from collections import OrderedDict


class LRUDict(OrderedDict):

	def __init__(self, max_len):
		super().__init__()
		self._size = max_len
		self._cache = OrderedDict()

	def __getitem__(self, key):
		if key in self._cache.keys():
			value = self._cache.pop(key)
			self._cache[key] = value
			return value
		else:
			return None

	def has_key(self, key):
		if key in self._cache.keys():
			return True
		return False

	def __setitem__(self, key, value):
		if key in self._cache.keys():
			self._cache.pop(key)
			self._cache[key] = value
		elif len(self._cache) == self._size:
			self._cache.popitem(last=False)
			self._cache[key] = value
		else:
			self._cache[key] = value

	def __len__(self):
		return len(self._cache)

	def __repr__(self):
		return dict(self._cache).__repr__()

	def __str__(self):
		return dict(self._cache).__str__()

	def reset(self):
		self.__init__(self._size)