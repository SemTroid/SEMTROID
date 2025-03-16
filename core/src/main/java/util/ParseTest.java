package main.java.util;

import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import main.java.config.Settings;
import main.java.dataType.*;

import main.java.runner.RepairRunner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/14 16:31
 */
public class ParseTest {
    private static Logger logger = LoggerFactory.getLogger(ParseTest.class);
    // 存解析的测试语句
    private static EnhancedTestCase tc;
    // folder 表示提取到的语义信息保存的目录
    private static String folder;
    private DesiredCapabilities capabilities;

    public ParseTest(String folder) {
        ParseTest.folder = folder;
        capabilities = new DesiredCapabilities();
    }

    public static String getFolder() {
        return folder;
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    // 传入测试用例的路径，解析测试用例，保存为EnhancedTestCase（Statements）
    // 对崩溃测试用例进行解析并以 JSON 形式存储到 folder 中
    public EnhancedTestCase parseAndSerialize(String pathToTestCase, String isCorrect) {
        CompilationUnit cu = null;
        // Add: 传入两个参数：测试用例路径，是否需要读取Trace结果并加入EnhancedMobileElement对象
        List<String> parameters = new ArrayList<>();
        parameters.add(pathToTestCase);
        parameters.add(isCorrect);

        try {
            cu = JavaParser.parse(new File(pathToTestCase));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        new MethodVisitor().visit(cu, parameters);
        // 以 JSON 格式保存测试用例，可以舍弃
//        UtilsParser.serializeTestCase(tc, pathToTestCase, folder);

        return tc;
    }

    /**
     * 生成修复后的测试用例
     * @param newTest 已修复测试用例
     * @param oldPath 旧测试用例绝对路径
     * 新测试用例路径，示例：output\RepairedTC\LarkPlayer\TC1.java
     */
    public void parseAndSaveToJava(EnhancedTestCase newTest, String oldPath) throws IOException {
        tc = newTest;
        CompilationUnit cu = null;

        try {
            cu = JavaParser.parse(new File(oldPath));
        } catch (ParseException | IOException e) {
            logger.error("An error occurred while parsing a java file!");
            e.printStackTrace();
        }

        // 获取并修改包名，例：LarkPlayer
        String newPath = newTest.getPath();
        String packageName = newPath.substring(0, newPath.lastIndexOf("\\"));
        packageName = packageName.substring(packageName.lastIndexOf("\\") + 1);
        // 修改包的声明
        new PackageVisitor().visit(cu, packageName);

        // 修改语句
        new ChangeMethodVisitor().visit(cu, newPath);

        // 持久化新测试用例到文件
        String source = cu.toString();
        File file = new File(newPath);
        FileUtils.touch(file);
        FileUtils.write(file, source, Charset.defaultCharset());
    }

    // Class
    // MethodVisitor for parseAndSerialize()
    // PackageVisitor and ChangeMethodVisitor for parseAndSaveToJava()
    private class MethodVisitor extends VoidVisitorAdapter<Object> {
        @Override
        public void visit(MethodDeclaration m, Object arg) {
            // 提取 DesiredCapabilities，传入capabilities (Before注解下的部分)
            if (m.getAnnotations() != null && m.getAnnotations().get(0).getName().getName().equals("Before")) {
                for (Statement st: m.getBody().getStmts()) {
                    if (st.toString().contains("setCapability")) {
                        String tempStr = st.toString();
                        String[] keyValue = StringUtils.substringsBetween(tempStr, "(", ")")[0].split(", ");
                        // 提取 capabilityName
                        String key = keyValue[0].replace("\"", "");
                        // 提取 value
                        if ("true".equals(keyValue[1]) || "false".equals(keyValue[1])) {
                            capabilities.setCapability(key, Boolean.parseBoolean(keyValue[1]));
                        } else {
                            capabilities.setCapability(key, keyValue[1].replace("\"", ""));
                        }
                    }
                }
            }
            // 提取测试用例核心内容存入tc  (Test注解下的部分，忽略了sleep语句)
            else if (m.getAnnotations() != null && m.getAnnotations().get(0).getName().getName().equals("Test")) {
                // 测试用例路径
                String fullPath = ((ArrayList<String>)arg).get(0);
                String testCaseName = fullPath.substring(fullPath.lastIndexOf("\\") + 1).replace(Settings.JAVA_EXT, "");
                // 新建测试语句集合tc，传入测试用例名，测试用例的路径
                tc = new EnhancedTestCase(testCaseName, fullPath);
                // 解析从测试用例文件中读取的每条语句
                for (Statement st : m.getBody().getStmts()) {
                    // 记录该测试语句所在行号
                    int line = st.getBeginLine();

                    // 记录测试语句
                    // 1 对界面元素操作
                    // 将行号、操作类型、操作值存入EnhancedMobileElement对象
                    if (st.toString().contains("driver.findElement")) {
                        EnhancedMobileElement eme = new EnhancedMobileElement(line);

                        if (st.toString().contains("click()")) {
                            eme.setAction(AppiumAction.Click);
                            eme.setValue("");
                        } else if (st.toString().contains("sendKeys")) {
                            eme.setAction(AppiumAction.SendKeys);
                            try {
                                eme.setValue(UtilsParser.getValueFromSendKeys(st.toString()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (st.toString().contains("clear()")) {
                            eme.setAction(AppiumAction.Clear);
                            eme.setValue("");
                        } else if(st.toString().contains("getText()")) {
                            // 断言语句的预期值
                            String ExpectValue = StringUtils.substringBetween(st.toString(),"\"","\"");
                            eme.setAction(AppiumAction.getText);
                            eme.setValue(ExpectValue);
                        }

                        try {
                            // Add: 对于getText()语句，截取 driver.findElement... 部分，再提取定位器
                            String temp = st.toString();
                            if(eme.getAction().equals(AppiumAction.getText)){
                                int indexLeft = temp.indexOf(",");
                                int indexRight = temp.lastIndexOf(")");
                                // 截取 driver.findElementByXXX("xxx").getText() 部分
                                temp = temp.substring(indexLeft+1,indexRight).trim();
                            }
                            // Add: 对于sendKeys语句，截取driver.findElement().sendKeys部分。删掉sendKeys输入值当中的. ，避免影响后面的处理。
                            if(eme.getAction().equals(AppiumAction.SendKeys)){
                                int index = temp.indexOf("sendKeys");
                                temp = temp.substring(0,index+8);
                            }
                            // 保存定位器对象
                            eme.setLocator(UtilsParser.getAppiumLocator(temp));
                            // 如果解析的是标准答案，无需提取保存trace的信息
                            if(((ArrayList<String>)arg).get(1).equals("false")){
                                // 从trace保存的ViewTreeInfo文件中读取其他信息保存到EnhancedMobileElement
                                ViewTreeInfo info = UtilsFileGetter.getViewTreeInfoFromJsonFile(testCaseName, line, "viewTreeInfo", getFolder());
                                eme.setXpath(info.getXpath());
                                eme.setClassName(info.getClassName());
                                eme.setResourceId(info.getResourceId());
                                eme.setContentDesc(info.getContentDesc());
                                eme.setText(info.getText());
                                eme.setImage(info.getImage()); // Add
                                eme.setCheckable(info.isCheckable());
                                eme.setClickable(info.isClickable());
                                eme.setScrollable(info.isScrollable());
                                eme.setFocusable(info.isFocusable());
                                eme.setLongClickable(info.isLongClickable());
                                eme.setCoordinate(new Point(info.getX(), info.getY()));
                                eme.setDimension(new Dimension(info.getWidth(), info.getHeight()));
                                // (Add)从trace保存的序列化文件中读取上下文信息保存到EnhancedMobileElement
                                Context context = (Context) main.java.util.FileUtils.readObject(folder + Settings.sep + testCaseName + Settings.sep + line+ "-contextInfo");
                                eme.setContext(context);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        tc.addStatementAtPosition(line, eme);
                    }
                    // 2 界面跳转（回退、前进、刷新）
                    else if (st.toString().contains("driver.navigate()")) {
                        EnhancedNavigate nav = null;

                        try {
                            if (st.toString().contains("back()")) {
                                nav = new EnhancedNavigate(line, "back");
                            } else if (st.toString().contains("forward()")) {
                                nav = new EnhancedNavigate(line, "forward");
                            } else if (st.toString().contains("refresh()")) {
                                nav = new EnhancedNavigate(line, "refresh");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (nav != null) {
                            tc.addStatementAtPosition(line, nav);
                        }
                    }
                    // 3 界面滑动、点击
                    else if (st.toString().contains("new TouchAction(driver)")) {
                        EnhancedTouchAction touchAction = null;
                        String temp = st.toString().replace("new TouchAction(driver).", "");
                        // Add: press语句执行滑动报错，使用longPress
                        if (temp.startsWith("longPress") && temp.contains("moveTo") && temp.contains("release().perform()")) {
                            touchAction = new EnhancedTouchAction(line, "swipe");
                        } else if (temp.startsWith("tap") && temp.contains("perform()")) {
                            touchAction = new EnhancedTouchAction(line, "tap");
                        }

                        if (touchAction != null) {
                            if (touchAction.getValue().equals("swipe")) {
                                try {
                                    // 获取界面滑动时的初始坐标点和结束坐标点
                                    touchAction = UtilsParser.getSwipePoints(touchAction, st.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else if(touchAction.getValue().equals("tap")){
                                try {
                                    // 获取tap操作的坐标点
                                    touchAction = UtilsParser.getTapPoint(touchAction, st.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            tc.addStatementAtPosition(line, touchAction);
                        }

                    }
                }
            }
        }
    }

    private class PackageVisitor extends VoidVisitorAdapter<Object> {
        @Override
        public void visit(PackageDeclaration n, Object arg) {
            n.setName(new NameExpr(arg.toString()));
        }
    }

    private class ChangeMethodVisitor extends VoidVisitorAdapter<Object> {
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            if (n.getAnnotations() != null && n.getAnnotations().get(0).getName().getName().equals("Test")) {
                BlockStmt newBlockStmt = new BlockStmt();

                for (Integer i : tc.getStatements().keySet()) {
                    String stm = "//";
                    // Add: 避免空指针异常，被删除的语句用注释符号代替
                    if(tc.getStatements().get(i)!=null){
                        stm = tc.getStatements().get(i).toString();
                    }
                    if (stm.contains("findElementByXPath")) {
                        String bracket = StringUtils.substringBetween(stm, "[", "]");
                        if (bracket != null) {
                            String bracketPlus = bracket.replaceAll("\"", "\\\\\"");
                            stm = stm.replace(bracket, bracketPlus);
                        }
                    }
                    ASTHelper.addStmt(newBlockStmt, new NameExpr(stm));
                }
                n.setBody(newBlockStmt);
            }
        }
    }


}
