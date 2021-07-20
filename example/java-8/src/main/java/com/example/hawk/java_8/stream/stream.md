sequential	    返回一个相等的串行的Stream对象，如果原Stream对象已经是串行就可能会返回原对象
parallel	    返回一个相等的并行的Stream对象，如果原Stream对象已经是并行的就会返回原对象
unordered	    返回一个不关心顺序的Stream对象，如果原对象已经是这类型的对象就会返回原对象
onClose	        返回一个相等的Steam对象，同时新的Stream对象在执行Close方法时会调用传入的Runnable对象
close	        关闭Stream对象
filter	        元素过滤：对Stream对象按指定的Predicate进行过滤，返回的Stream对象中仅包含未被过滤的元素
map	            元素一对一转换：使用传入的Function对象对Stream中的所有元素进行处理，返回的Stream对象中的元素为原元素处理后的结果
mapToInt	    元素一对一转换：将原Stream中的使用传入的IntFunction加工后返回一个IntStream对象
flatMap	        元素一对多转换：对原Stream中的所有元素进行操作，每个元素会有一个或者多个结果，然后将返回的所有元素组合成一个统一的Stream并返回；
distinct	    去重：返回一个去重后的Stream对象
sorted	        排序：返回排序后的Stream对象
peek	        使用传入的Consumer对象对所有元素进行消费后，返回一个新的包含所有原来元素的Stream对象
limit	        获取有限个元素组成新的Stream对象返回
skip	        抛弃前指定个元素后使用剩下的元素组成新的Stream返回
takeWhile	    如果Stream是有序的（Ordered），那么返回最长命中序列（符合传入的Predicate的最长命中序列）组成的Stream；如果是无序的，那么返回的是所有符合传入的Predicate的元素序列组成的Stream。
dropWhile	    与takeWhile相反，如果是有序的，返回除最长命中序列外的所有元素组成的Stream；如果是无序的，返回所有未命中的元素组成的Stream。

终止操作方法	说明

iterator	    返回Stream中所有对象的迭代器;
spliterator	    返回对所有对象进行的spliterator对象
forEach	        对所有元素进行迭代处理，无返回值
forEachOrdered	按Stream的Encounter所决定的序列进行迭代处理，无返回值
toArray	        返回所有元素的数组
reduce	        使用一个初始化的值，与Stream中的元素一一做传入的二合运算后返回最终的值。每与一个元素做运算后的结果，再与下一个元素做运算。它不保证会按序列执行整个过程。
collect	        根据传入参数做相关汇聚计算
min	            返回所有元素中最小值的Optional对象；如果Stream中无任何元素，那么返回的Optional对象为Empty
max	            与Min相反
count	        所有元素个数
anyMatch	    只要其中有一个元素满足传入的Predicate时返回True，否则返回False
allMatch	    所有元素均满足传入的Predicate时返回True，否则False
noneMatch	    所有元素均不满足传入的Predicate时返回True，否则False
findFirst	    返回第一个元素的Optioanl对象；如果无元素返回的是空的Optional； 如果Stream是无序的，那么任何元素都可能被返回。
findAny	        返回任意一个元素的Optional对象，如果无元素返回的是空的Optioanl。
isParallel	    判断是否当前Stream对象是并行的