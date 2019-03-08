/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.24-log : Database - q2library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`q2library` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `q2library`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `aid` char(32) NOT NULL,
  `aname` varchar(20) DEFAULT NULL,
  `apassword` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`aid`,`aname`,`apassword`) values 
('425120365D1347D4991A662E22E50647','q2library','q2library');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bid` char(32) NOT NULL,
  `bname` varchar(100) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `publish` varchar(50) DEFAULT NULL,
  `pubdate` varchar(30) DEFAULT NULL,
  `btype` varchar(10) DEFAULT NULL,
  `bindex` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`bid`,`bname`,`author`,`publish`,`pubdate`,`btype`,`bindex`) values 
('425120365D1347D4991A662E22E50611','网络知识应用与网页制作','陈海林','清华大学出版社','2004.08','计算机','TP393/C45c'),
('425120365D1347D4991A662E22E50612','网络知识应用与网页制作','谢从华,高蕴梅,黄晓华','清华大学出版社','2018.06','计算机','TP393/X54q'),
('425120365D1347D4991A662E22E50613','网络信息安全基础','黄林国','清华大学出版社','2018.01','计算机','TP393.08/H76'),
('425120365D1347D4991A662E22E50614','局域网技术与组网工程','苗凤君,夏冰','清华大学出版社','2018.08','计算机','TP393.1/M67'),
('425120365D1347D4991A662E22E50615','网络信息安全及管理研究','邹瑛','北京理工大学出版社','2018.03','计算机','TP393.08/Z94'),
('425120365D1347D4991A662E22E50616','深入浅出HTTPS：从原理到实战','肖佳','电子工业出版社','2018.06','计算机','TP393.08/Y78'),
('425120365D1347D4991A662E22E50617','网络概论','陈明,张永斌','北京理工大学出版社','2014.03','计算机','TP393/C46ae'),
('425120365D1347D4991A662E22E50618','新编计算机网络习题与解析','鲁士文','清华大学出版社','2013.07','计算机','TP393-44/L84a'),
('425120365D1347D4991A662E22E50619','Cisco路由器配置与管理完全手册','王达','中国水利水电出版社','2013.07','计算机','TN915.05-62/W31a'),
('425120365D1347D4991A662E22E50620','黄金投资','桂詠评','上海人民出版社','2013.06','经济','F830.94-43/G91'),
('425120365D1347D4991A662E22E50621','城市信用社的业务与管理','唐旭,高洪星','中国金融出版社','1994.05','经济','F932.29/T28'),
('425120365D1347D4991A662E22E50622','阳光立业','郑作时,陈斯文','人民日报出版社','2015.08','经济','F842.9/Z59'),
('425120365D1347D4991A662E22E50623','中国保险业制度变迁与绩效研究','朱文胜','中国金融出版社','2005.01','经济','F842.9/Z83'),
('425120365D1347D4991A662E22E50624','香港的保险业','李文群,尧金仁','清华大学出版社','2008.07','经济','F842.765.8/L35'),
('425120365D1347D4991A662E22E50625','人寿保险--理财新工具','赵猛','中国金融出版社','2006.12','经济','F842.62/Z45'),
('425120365D1347D4991A662E22E50626','国有股流通','郭洪涛','华南理工大学出版社','2008.06','经济','F832.51/G94c'),
('425120365D1347D4991A662E22E50627','中国股市：轮回中的涅槃','何诚颖','中国财政经济出版社','2009.04','经济','F832.51/H31c'),
('425120365D1347D4991A662E22E50628','惠特曼诗选','沃尔特·惠特曼','人民文学出版社','2018.07','文学','I712.24/H87aa'),
('425120365D1347D4991A662E22E50629','解读博尔赫斯','残雪','人民文学出版社','2000.06','文学','I873.074/C16'),
('425120365D1347D4991A662E22E50630','中国鼓词文学发展史','李雪梅','上海人民出版社','2012.01','文学','I207.39/L35'),
('425120365D1347D4991A662E22E50631','美国黑色幽默小说欣赏','周静琼','华南理工大学出版社','2006.09','文学','I712.074/Z74'),
('425120365D1347D4991A662E22E50632','夜与昼','柯云路','人民文学出版社','1986.08','文学','I247.5/K36da'),
('425120365D1347D4991A662E22E50633','青年近卫军','法捷耶夫','人民文学出版社','1975.01','文学','I512.45/F14'),
('425120365D1347D4991A662E22E50634','新星','柯云路','江苏凤凰文艺出版社','1985.05','文学','I247.57/K36ab'),
('425120365D1347D4991A662E22E50635','建筑景观地貌学','王红','科学出版社','2018.07','建筑','TU986.2/W33h'),
('425120365D1347D4991A662E22E50636','此心有路 何必慌张','沈万九','机械工业出版社','2018.07','哲学','B821/S44'),
('425120365D1347D4991A662E22E50637','亲密关系：敏感的心灵该如何安放?','伊莱恩·阿伦','中国人民大学出版社','2018.07','心理学','C912.11/A11c'),
('425120365D1347D4991A662E22E50638','中国前卫艺术的兴起','汪民安,宋晓萍','北京大学出版社','2018.10','历史','J120.97/W28'),
('425120365D1347D4991A662E22E50639','简明中药使用手册','阎萍,王燕,张楠','化学工业出版社','2018.06','医学','R28/Y17'),
('425120365D1347D4991A662E22E50640','从零开始·吉他超级入门','野村大辅','人民邮电出版社','2018.04','艺术','J623.26/Y39'),
('425120365D1347D4991A662E22E50641','看图自学吉他和弦','朱利安·海曼','人民邮电出版社','2017.02','艺术','J623.26/H12'),
('425120365D1347D4991A662E22E50642','流行钢琴节奏与和弦教程','陈飞','人民邮电出版社','2017.01','艺术','J624.16/C44'),
('425120365D1347D4991A662E22E50643','大国根基：中国农村改革40年','宋洪远','广东经济出版社','2018.03','经济','F320.2/S86'),
('425120365D1347D4991A662E22E50644','先行者的探索：广东改革开放40年','王珺,赵祥','广东经济出版社','2018.02','经济','D619.65/W31'),
('425120365D1347D4991A662E22E50645','秋叶','上海音乐出版社','上海音乐出版社','1991.09','艺术','J623.2/S33aa'),
('425120365D1347D4991A662E22E50646','五洲同唱','褚建华','蓝天出版社','2005.02','艺术','J657.32/Z74'),
('425120365D1347D4991A662E22E50647','带一本书去杭州','范军','科学技术文献出版社','2005.04','地理','K925.51/F23'),
('425120365D1347D4991A662E22E50648','出土简帛中的政治哲学','欧阳祯人','中国人民大学出版社','2017.06','地理','K877.54/O15'),
('425120365D1347D4991A662E22E50649','湖南全省掌故备考','王先谦','岳麓书社','2009.11','地理','K296.4/W37'),
('425120365D1347D4991A662E22E50650','国宝1933-1949：故宫博物院珍藏文物流迁真相','窦应泰','新世界出版社','2012.01','地理','K870.4/D72'),
('425120365D1347D4991A662E22E50651','中国改革为什么能成功','徐斌','世界图书出版有限公司北京公司','2018.04','政治','D61/X74da'),
('425120365D1347D4991A662E22E50652','国际人权公约与中国','莫纪宏','世界知识出版社','2005.07','政治','D998.2/M86'),
('425120365D1347D4991A662E22E50653','国际知识产权政治问题研究','刘银良','知识产权出版社','2014.11','政治','D997.1/L76b'),
('425120365D1347D4991A662E22E50654','国际关系视角下的国际私法问题','孙建','人民出版社','2007.08','政治','D997/S96'),
('425120365D1347D4991A662E22E50655','美国历史上的总统、最高法院及宪政领导权','基斯·威廷顿','北京大学出版社','2010.01','政治','D971.26/H87'),
('425120365D1347D4991A662E22E50656','致命元素：毒药的历史','约翰·埃姆斯利','生活·读书·新知三联书店','2012.06','医学','R99-49/A13'),
('425120365D1347D4991A662E22E50657','神经毒理学','陈景元','人民卫生出版社','2016.01','医学','R99/C45'),
('425120365D1347D4991A662E22E50658','中国药品安全治理现代化','胡颖廉','中国医药科技出版社','2017.01','医学','R954/H53'),
('425120365D1347D4991A662E22E50659','荨麻疹','朱文元','东南大学出版社','2001.01','医学','R758.24/Z83'),
('425120365D1347D4991A662E22E50660','行为疗法','丽莎白·罗默','重庆大学出版社','2016.11','医学','R749.05/A21'),
('44E74561682F412F8C6876503B5EED34','今年出的新书2019','网站制作者','xx出版社','2019.01','计算机','xxxxx');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `fid` char(32) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `ftime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

insert  into `feedback`(`fid`,`title`,`content`,`phone`,`ftime`) values 
('421120365D1347D4991A662E22E50600','图书馆提供免费知网查重吗？','您好老师，图书馆是否提供免费知网论文查重。谢谢。','13613416611','2018-05-03'),
('421120365D1347D4991A662E22E50601','关于本科生借阅总量','希望能增加本科生的借阅总量，仅能借8本书不能满足现在大学生的需求，\n而且与研究生借阅总量相比显得有些少了，最好可以增加到10~12本或者以上，\n望采纳意见！','13613416611','2018-09-30'),
('421120365D1347D4991A662E22E50602','学生在校外如何使用电子资源','管理员，你好。学生读者在校外能否访问校内的电子资源，\n如果可以，要怎样做？谢谢！','15219466201','2018-05-05'),
('421120365D1347D4991A662E22E50603','关于电子邮箱借阅操作','图书管理员你好，我想知道一下，怎么留下我的邮箱和想要的阅读资料，\n然后管理员会发一些相关文章过来呢？\n我想要阅读一下《毛泽东选集》中的矛盾论，谢谢。','15219466201','2018-03-15'),
('421120365D1347D4991A662E22E50604','手机静音','管理员：        \n你好！我是图书馆的忠实读者，几乎每天都会在图书馆二楼备考。\n开学以来，发现不少来自习的同学无视服务台旁侧大显示屏的入馆须知，\n没能把手机调成静音模式，各种信息提示声间断响起，有时同一个人的手机就响好几遍，\n这样严重干扰了其他读者的自习状态。不仅对那些能保持安静的读者极其不公平，\n更会挫伤他们的继续保持良好行为习惯的积极性。毕竟安静的环境氛围是靠大家共同维持的，\n针对那些公共环境意识淡薄的同学，希望本馆能采取切实有效、富有针对性的措施来处理这类问题。\n谢谢！\n','15036222256','2018-03-14'),
('421120365D1347D4991A662E22E50605','关于电子阅览的使用','老师您好,\n我是校外读者,请问在刷卡使用电子阅览的电脑时输入的账号和密码是什么?\n因为最近图书馆的wifi用不了,所以需要借用电子阅览的电脑.谢谢老师\n','18438888133','2017-09-30'),
('421120365D1347D4991A662E22E50606','咨询校友办图书证的情况','请问： \n1、能否让人代办呢？看图书馆公告都是只能在周一至周五的上班时间办呢！ \n2、几年前在校的图书卡还能继续用吗？如不能用，需带什么资料去申请***呢？ \n3、能否让周末也能***啊！上班时间都走不开呢\n','18876941131','2017-08-31'),
('421120365D1347D4991A662E22E50607','音像资料下载','图书馆管理员： \n        您好！我是五邑大学图书馆的读者，这个暑假在图书馆备考司法考试。\n请问随书配套光盘资料在哪下载？以及自习室的空调能否把温度设定调整在国家\n对于公共场所空调温度设定标准范围内（26摄氏度左右），及节能又健康！\n        谢谢！\n','18876622089','2017-08-07'),
('421120365D1347D4991A662E22E50608','暑假到期的书籍最迟什么时候还','你好！\n请问暑假期间借期已满的书籍是必须要在借期已满以前归还吗？\n','13715150077','2017-07-13'),
('421120365D1347D4991A662E22E50609','光盘资料下载不了','\n图书馆官网那里有个下载教程，按照它方法一操作，到了最后的界面，\n那个“随书光盘下载”的链接没有，麻烦工作人员跟进看看，谢谢。\n','18351078990','2017-07-11'),
('425120365D1347D4991A662E22E50646','关于增加英语四六级新书','您好:\n   作为正处于准备英语四级考试的学生，我发现图书馆内的关于英语四级的书籍比较老旧。主要是英语四级近年来经过大改革，题型大变。\n   而图书馆内的英语四级书都没法参考和学习。所以，我建议图书馆能选购一些最新的英语四六级书，帮助学生通过英语四级。\n   谢谢！','13246874458','2018-12-07');

/*Table structure for table `inform` */

DROP TABLE IF EXISTS `inform`;

CREATE TABLE `inform` (
  `iid` char(32) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `itime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inform` */

insert  into `inform`(`iid`,`title`,`content`,`itime`) values 
('415120365D1347D4991A662E22E50600','关于校运会期间图书馆闭馆的通知','各位读者：\n    根据学校工作安排，2018年11月9日（周五）、11月10日（周六）举行全校运动会。运动会期间图书馆开放安排如下：\n    1、学科馆（含借书）11月9、10日闭馆两天\n    2、伟伦图书馆（旧馆）阅览室11月9-11日闭馆三天\n    请读者根据自己的学习需要，调整好图书借阅时间。\n','2018-11-06'),
('415120365D1347D4991A662E22E50601','图书馆关于停电闭馆的通知','各位读者:\n    因学校南区高压电房迁移施工停电一天，图书馆本周日（2018年12月9日）闭馆一天，请各位读者相互转告，根据自己的需要调整好图书借阅时间。\n    不便之处，敬请谅解。\n','2018-12-07'),
('415120365D1347D4991A662E22E50602','关于新东方英语四六级12月考试必备直播安排的通知 ','直播主题：四六级12月考试必备秘籍\r\n直播时间： 11月23日 19:00-20:00\r\n直播名师：王兆飞 \r\n新东方20周年功勋教师，外交学院英语专业文学硕士，主讲四级、六级、考研、考博、专八、同等学力等各类英语语考试的写作。深厚的英语功底和多年的教学经验，\r\n使他的课精准独到、详尽实用；\r\n对各种写作题型的深入分析使你在短时间内掌握万能作文写法；对文学的热爱和对人生的乐观使他的课深入浅出、寓教于乐。上他的课，你会在笑声中学会写作，\r\n爱上写作。\r\n','2018-11-01'),
('415120365D1347D4991A662E22E50603','关于提高本科生图书借阅册数的通知\n','各位读者：\n   为满足读者借阅图书的需求，提高图书馆图书借阅率，从即日起，全日制本科生的借书量由8册提高到10册。\n \n   特此通知！\n','2018-10-30'),
('415120365D1347D4991A662E22E50604','关于图书馆国庆节开馆的通知\n','各位读者：\n根据学校2018年国庆节的放假安排，图书馆国庆节(2018年10月1日 -10月3日)闭馆3天，其它时间正常开放，请各位读者调整好图书借阅时间。\n','2018-09-26'),
('415120365D1347D4991A662E22E50606','2018级新生电子资源利用培训系列讲座之一 ','一：学海无涯，知网作舟——2018CNKI培训活动\r\n讲座时间：10月9日下午2：30\r\n讲座地点：五邑大学北主楼202教室\r\n主讲人：刘娜，高级培训讲师，产品专员，香港大学硕士毕业。主要为高等院校，党政机关，科研院所和公共图书馆提供培训。拥有独特的教学风格，善于挖掘用户\r\n潜在需求，从而实现知识管理与服务的价值最大化。\r\n培训内容：\r\n1.中国知网（CNKI）简介&了解数字资源类型；\r\n2.CNKI新平台的功能，使用方法和检索技巧；\r\n3.如何利用CNKI的相关资源进行论文选题；\r\n4.如何利用研学平台进行论文写作（文献管理、记录笔记等）；\r\n5.如何在CNKI中遴选本学科高质量、核心期刊信息进行投稿；\r\n6.移动端——全球学术快报的使用介绍。\r\n','2018-09-20'),
('415120365D1347D4991A662E22E50607','关于新生入馆考试及开通借阅权限的操作说明  ','图书馆新生入馆教育是五邑大学新生入学教育的重要组成部分。通过入馆教育让新生尽快掌握图书馆资源的利用方法，对新生今后的学习以及综合素质的提高奠定良好\r\n的基础。现将2018级新生入馆教育的相关事项说明如下：\r\n1、新生请认真学习学生手册中《图书馆服务管理规定》以及图书馆网页上\"\"新生专栏\"\"的相关知识。图书馆主页网址： http://lib.wyu.edu.cn。\r\n2、新生在掌握图书馆基本使用方法后，请自主进行在线考试.。考核对象是2018级本科新生及专升本插班生，考核合格后才能开通图书借阅权限！\r\n3、新生可通过个人电脑、手机进入考试系统。\r\n4、入馆考试系统使用说明  \r\n','2018-08-31'),
('415120365D1347D4991A662E22E50609','关于开通e搜（e-search）试用的通知   ','e搜中外文资源发现系统（下称e搜）是北京一博千禧科技有限公司推出的一项突破性服务，是一种可以提供图书馆馆藏资源、图书馆订购的电子资源以及开放获取资源\r\n和其它馆外资源、图书馆特藏资源等全部类型的中外文资源的统一发现与获取服务，为读者提供单一入口的资源发现与获取服务用户环境。利用e搜，只需使用单一检索\r\n框，可以让您像是使用谷歌学术一样以一个简单的方式“一站式”检索图书馆订阅的大部分外文电子资源（包括不限于期刊文章、数据库、报纸文章、电子书籍及其他更\r\n多资料等）。如果选择“加入本馆馆藏之外的更多结果”，可搜索到超过20亿条原数据记录。对于本馆没购买资源可通过文献传递获取。如试用获得读者认同，图书馆将\r\n添加纸本馆藏、配置和更新更多自建资源在统一索引当中。\r\n试用网址：http://esr.yibo365.net\r\n试用时间：2018年5月10日-2018年8月9日\r\n主题分类：综合\r\n文献类型：工具，资源\r\n揭示深度：全文\r\n内容语种：综合，中、外文，含小语种','2018-05-10'),
('415120365D1347D4991A662E22E50610','关于图书馆2018年中秋节开馆时间的通知   \n','根据学校2018年中秋节放假工作安排，图书馆中秋节（9月24日，星期一）闭馆一天，9月22日（星期六）、9月23日（星期日）正常开放。\n    请读者相互转告周知。\n','2018-09-20'),
('415120365D1347D4991A662E22E50611','关于图书馆暑假开放的通知   \n','各位读者：\n \n    图书馆今年暑假期间开放具体安排如下： \n    1、开放日期：7月17日-8月30日。 \n    2、开放时间：早上8：00－下午5：30，中午不休。 \n    3、开放模式：借还图书，集中开放二楼经济馆。 \n   \n 请读者相互告知，并祝大家假期愉快！ \n','2018-07-11');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
