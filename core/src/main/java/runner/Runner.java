package main.java.runner;

public class Runner {

    private static String s = "\"img1:[B@495b0487 img2:[B@55dfcc6\\n" +
 " \"img1:[B@93cf163 img2:[B@55dfcc6\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** Element Similarity: 0.9999998807907104 ***\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** No Breakage ***\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - *** Click Element: ById(\\\"com.android.camera2:id/shutter_button\\\") ***\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - ***** 是否执行当前语句修复 (1-Yes)(其他-跳过当前语句) *****\\n" +
 " \"1\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - Check statement: 49: driver.findElementById(\\\"com.android.camera2:id/done_button\\\").click()\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - *** 1 Retrieve Element From Appium Locator ***\\n" +
 " \"img1:[B@49d3c823 img2:[B@436bc36\\n" +
 " \"img1:[B@5fe1ce85 img2:[B@436bc36\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** Element Similarity: 0.9999999403953552 ***\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** No Breakage ***\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - *** Click Element: ById(\\\"com.android.camera2:id/done_button\\\") ***\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - ***** 是否执行当前语句修复 (1-Yes)(其他-跳过当前语句) *****\\n" +
 " \"1\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - Check statement: 50: driver.findElementById(\\\"com.ichi2.anki:id/multimedia_edit_field_done\\\").click()\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - *** 1 Retrieve Element From Appium Locator ***\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** Breakage, start repair *** \\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** Breakage Type: Element not found (ENF) ***\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - *** 2 Retrieve Element From Current Screen ***\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** Size of candidate elements in current screen: 3 ***\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.widget.TextView[1] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"text1:Do you want to crop this image? img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.09357229620218277\\n" +
 " \"综合相似度：0.09357229620218277\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[2] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"text1:OK img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.5415367484092712\\n" +
 " \"综合相似度：0.5415367484092712\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[1] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"text1:NO img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.4588838815689087\\n" +
 " \"综合相似度：0.4588838815689087\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - ***** 当前界面获取不到满足阈值的元素，是否进行上下文增强搜索?(0-False/1-True) *****\\n" +
 " \"0\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - 非click语句 或 当前界面未找到目标元素\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - ***** 当前界面未搜索到目标元素，是否进行路径搜索?(0-false/1-true) *****\\n" +
 " \"1\\n" +
 " \"界面无可滑动元素\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - ***** 滑动后未搜索到目标元素，是否进行路径搜索(点击)?(0-false/1-true) *****\\n" +
 " \"1\\n" +
 " \"[main] INFO main.java.runner.RepairRunner - 尝试搜索相邻界面\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - 可点击元素数量：3\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - 未搜索到 More Options元素\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - Click Element: ById(\\\"com.ichi2.anki:id/md_content\\\")\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - Please determine whether to search on this page: (0-false/1-true)\\n" +
 " \"0\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** 未搜索此界面，返回原界面 ***\\n" +
 " \"1\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - Click Element: ById(\\\"com.ichi2.anki:id/md_buttonDefaultNegative\\\")\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - Please determine whether to search on this page: (0-false/1-true)\\n" +
 " \"1\\n" +
 " \"[main] INFO main.java.util.UtilsRepair - *** Size of candidate elements in current screen: 9 ***\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Button[3] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"text1:CROP IMAGE img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.05102851241827011\\n" +
 " \"综合相似度：0.05102851241827011\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/android.widget.ImageButton[1] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"img1:[B@2e5c7f0b img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.4634498953819275\\n" +
 " \"综合相似度：0.6434499669075012\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.TextView[1] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"img1:[B@46cc127b img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.10631705820560455\\n" +
 " \"综合相似度：0.10631705820560455\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.TextView[2] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"img1:[B@352c308 img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.9999998807907104\\n" +
 " \"综合相似度：1.1799999523162843\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.ImageView[1] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"img1:[B@344344fa img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.32815563678741455\\n" +
 " \"综合相似度：0.5081557083129883\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Button[1] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"text1:GALLERY img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.24087873101234436\\n" +
 " \"综合相似度：0.24087873101234436\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[1] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"text1:Editing field img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.10801415145397186\\n" +
 " \"综合相似度：0.2880142229795456\\n" +
 " \"收集元素 //hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Button[2] 上下文信息：\\n" +
 " \"size of context: 2\\n" +
 " \"text1:CAMERA img2:[B@45efc20d\\n" +
 " \"个体相似度: 0.2759590744972229\\n" +
 " \"综合相似度：0.2759590744972229\\n" +
 " \"当前界面满足阈值的最大相似度：1.1799999523162843\";";
    public static void main(String[] args) throws InterruptedException {
        String[] lines = s.split("\\\\n");
        for (String line : lines) {
 if (!line.trim().isEmpty()) { // 删除多余空行
     System.out.println("\u001B[31m" + line + "\u001B[0m"); // 打印为红色
     Thread.sleep(2000); // 每隔2秒输出一行
 }
        }
    }

}
