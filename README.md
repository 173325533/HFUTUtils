# HFUTUtils
这是一个工具程序集合，方便我们平时处理数据。针对文本处理的内容较多。

详细的使用方法和案例参考：http://www.datalearner.com/blog/1051494253501911

-----------使用方法-----------

可以直接看源码文件，也可以直接下载jar包引入到工程中。注意，本项目使用jdk8+。使用Maven方式导入了Google Guava、Apache Commons等包。可以直接下载查看pom.xml文件后，添加到自己的项目中。

-----------分词用法-----------

分词集成了张华平分词 具体使用方式可参考 初始化NLPIR，传入Data文件夹和lib文件夹位置的参数，然后就可以分词了，注意授权文件的更新日期

NLPIR nlpir = new NLPIR("d:/nlpir/lib/win64/NLPIR","d:/nlpir/");

String output_text1 = nlpir.seg(input_text1, 0);

System.out.println(output_text1);


-----------语料模型用法-----------

本程序可以自动将这些单词变成索引形式，并将文档用SparseVSM表示。保存后生成三个文件：docIndex、wordIndex、sparseVSM。分别表示文档-索引、单词-索引和稀疏向量空间模型。（注意，输入语料是文档的时候，VSM一行对应之前的一行，因此docIndex为空。输入语料是文件夹时候，docIndex是文件名字-索引，VSM是按索引0-ndocs来的）。

//读取文件并保存语料

Corpus corpus = new Corpus(inputFile, false);

corpus.saveCorpus(inputFile);

//载入之前保存的语料

Corpus corpusLoading = new Corpus();

corpusLoading.loadCorpus(inputFile);

//输入是文件夹的测试，去掉false参数即可

String inputDir = "D:/test";

String outputDir = "d:/test_out";

Corpus corpusDir = new Corpus(inputDir);

corpusDir.saveCorpus(outputDir);
