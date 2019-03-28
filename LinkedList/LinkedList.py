#https://medium.freecodecamp.org/python-interview-question-guide-how-to-code-a-linked-list-fd77cbbd367d

#https://medium.com/@kojinoshiba/data-structures-in-python-series-1-linked-lists-d9f848537b4d

class linked_list():
	def __init__(self):
		self.head = node()

	def append(self, data):
		new_node = node(data)
		cur = self.head
		while cur.next != None:
			cur = cur.next
		cur.next = new_node

	def length(self):
		cur = self.head
		total = 0
		while  cur.next != None:
			total +=1
			cur = cur.next
		return total

	def display(self):
		elems = []
		cur_node = self.head
		while cur_node.next !=None:
			cur_node=cur_node.next
			elems.append(cur_node.data)
		print elems

	def get(self, index):
		if index >=self.length():
			print "ERROR: 'Get' Index out of range!"
			return None
		cur_idx=0
		cur_node=self.head
		while True:
			cur_node = cur_node.next
			if cur_idx==index:
				return cur_node.data
			cur_idx+=1

	def remove(self, index):
		if index >=self.length():
			print "ERROR: 'Get' Index out of range!"
			return None
		cur_idx=0
		cur_node=self.head
		while True:
			prev_node = cur_node
			cur_node = cur_node.next
			if cur_idx == index:
				prev_node.next = cur_node.next
				return
			cur_idx +=1


class node:
	def __init__(self, data=None):
		self.data=data
		self.next=None


my_list = linked_list()

my_list.append(1)
my_list.append(2)
my_list.append(3)
my_list.append(4)

my_list.display()

my_list.remove(2)

my_list.display()


