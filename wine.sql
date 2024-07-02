/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.20 : Database - red_wine
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`red_wine` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `red_wine`;

/*Table structure for table `administrators` */

DROP TABLE IF EXISTS `administrators`;

CREATE TABLE `administrators` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(16) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='管理员';

/*Data for the table `administrators` */

insert  into `administrators`(`id`,`name`,`password`) values (1,'admin','admin');
insert  into `administrators`(`id`,`name`,`password`) values (2,'1234','1234');

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `class_id` int(10) NOT NULL COMMENT '分类ID',
  `title` varchar(50) NOT NULL COMMENT '名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `specifications` varchar(10) NOT NULL COMMENT '规格',
  `place` varchar(30) NOT NULL COMMENT '产地',
  `alcohol` varchar(10) NOT NULL COMMENT '酒精度',
  `photo` varchar(255) NOT NULL COMMENT '图片名称',
  `content` text NOT NULL COMMENT '简介',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商品信息';

/*Data for the table `commodity` */

insert  into `commodity`(`id`,`class_id`,`title`,`price`,`specifications`,`place`,`alcohol`,`photo`,`content`) values (1,1,'张裕解百纳优选级','60.00','750ml/瓶','山东烟台','12%vol','20f5a2a2-7fd7-4324-9d95-c70eed9ecdbf1red.jpg','随着科学技术和葡萄酒学的进步和发展，人们对张裕葡萄酒的认识和了解不断地深入。例如，人们确定的葡萄酒的成分已经由1950年的50多种增加到1986年的600种(Baumes 1986), 在张裕葡萄酒中已鉴定出1000多种物质，其中有350多种已被定量鉴定（Navarre,1998）。但人们对张裕葡萄酒的某些物质,特别是芳香性物质的了解还很少。葡萄酒作为一种自然产品,作为一种营养丰富的自然饮料，仍然存在着很多秘密等待我们去探索。虽然我们现有的知识还远未揭开葡萄酒的秘密，但我们已经知道了张裕葡萄酒的主要成分。\r\n水 水是张裕葡萄酒的主要成份，约占70-90%。但张裕葡萄酒中的水，是葡萄植株的根系从土壤中直接吸收的，因此，是生物学纯水。它也是葡萄酒中其它物质的载体，正是这些物质，才使每一种张裕葡萄酒具有其个性和风格。水，是葡萄酒“生命”的源泉。\r\n酒精 即乙醇，它是酵母菌利用葡萄浆果中的糖进行发酵的主要产物。张裕葡萄酒中酒精的含量通常为7-16%(V/V)，但一些自然甜型张裕葡萄酒和加强张裕葡萄酒的酒精含量可达到23%(V/V)。酒精是张裕葡萄酒香气和风味物质的支撑物，它也使葡萄酒具有醇厚和结构感。\r\n糖和甘油 张裕葡萄酒中的糖通常是浆果中未经发酵的部分。干型张裕葡萄酒的含糖量低于4g/L，而甜型张裕葡萄酒中的含糖量可达到80g/L或更高。甘油是酒精发酵的主要副产物，其含量通常为5-12g/L。糖和甘油都可使张裕葡萄酒具圆润和肥硕感。\r\n酸 张裕葡萄酒中的酸主要有两大类：\r\n——葡萄浆果本身的酸：酒石酸、苹果酸和微量柠檬酸；\r\n——发酵产生的酸：乳酸、琥珀酸和醋酸等。\r\n张裕葡萄酒含酸量过低，则口味平淡，贮藏性差；相反，含量过高，则酒体粗糙、瘦弱。因此，张裕葡萄酒中酸的成份和含量可影响张裕葡萄酒的协调感和贮藏性。\r\n丹宁和色素 在红葡萄酒的酿造过程中，由于对果梗、果皮和种子的浸渍作用，使存在于其中的丹宁和色素溶解在葡萄酒中，其含量通常为1-5g/L。丹宁可影响葡萄酒的结构感和成熟特性；而色素则主要影响葡萄酒的颜色。\r\n其它物质 在张裕葡萄酒中，还含有很多其它的物质，如酯类、高级酯、脂肪酸、芳香物质、多种矿物质(包括微量元素)、微量的二氧化碳、三氧化硫以及多种维生素(VB1、VB2、VB6、VB12、Vc、Vh、Vp等)和各种氨基酸。');
insert  into `commodity`(`id`,`class_id`,`title`,`price`,`specifications`,`place`,`alcohol`,`photo`,`content`) values (2,1,'张裕解百纳特选级','60.00','750ml/瓶','山东烟台','12%vol','red_wine.jpg','随着科学技术和葡萄酒学的进步和发展，人们对张裕葡萄酒的认识和了解不断地深入。例如，人们确定的葡萄酒的成分已经由1950年的50多种增加到1986年的600种(Baumes 1986), 在张裕葡萄酒中已鉴定出1000多种物质，其中有350多种已被定量鉴定（Navarre,1998）。但人们对张裕葡萄酒的某些物质,特别是芳香性物质的了解还很少。葡萄酒作为一种自然产品,作为一种营养丰富的自然饮料，仍然存在着很多秘密等待我们去探索。虽然我们现有的知识还远未揭开葡萄酒的秘密，但我们已经知道了张裕葡萄酒的主要成分。\r\n水 水是张裕葡萄酒的主要成份，约占70-90%。但张裕葡萄酒中的水，是葡萄植株的根系从土壤中直接吸收的，因此，是生物学纯水。它也是葡萄酒中其它物质的载体，正是这些物质，才使每一种张裕葡萄酒具有其个性和风格。水，是葡萄酒“生命”的源泉。\r\n酒精 即乙醇，它是酵母菌利用葡萄浆果中的糖进行发酵的主要产物。张裕葡萄酒中酒精的含量通常为7-16%(V/V)，但一些自然甜型张裕葡萄酒和加强张裕葡萄酒的酒精含量可达到23%(V/V)。酒精是张裕葡萄酒香气和风味物质的支撑物，它也使葡萄酒具有醇厚和结构感。\r\n糖和甘油 张裕葡萄酒中的糖通常是浆果中未经发酵的部分。干型张裕葡萄酒的含糖量低于4g/L，而甜型张裕葡萄酒中的含糖量可达到80g/L或更高。甘油是酒精发酵的主要副产物，其含量通常为5-12g/L。糖和甘油都可使张裕葡萄酒具圆润和肥硕感。\r\n酸 张裕葡萄酒中的酸主要有两大类：\r\n——葡萄浆果本身的酸：酒石酸、苹果酸和微量柠檬酸；\r\n——发酵产生的酸：乳酸、琥珀酸和醋酸等。\r\n张裕葡萄酒含酸量过低，则口味平淡，贮藏性差；相反，含量过高，则酒体粗糙、瘦弱。因此，张裕葡萄酒中酸的成份和含量可影响张裕葡萄酒的协调感和贮藏性。\r\n丹宁和色素 在红葡萄酒的酿造过程中，由于对果梗、果皮和种子的浸渍作用，使存在于其中的丹宁和色素溶解在葡萄酒中，其含量通常为1-5g/L。丹宁可影响葡萄酒的结构感和成熟特性；而色素则主要影响葡萄酒的颜色。\r\n其它物质 在张裕葡萄酒中，还含有很多其它的物质，如酯类、高级酯、脂肪酸、芳香物质、多种矿物质(包括微量元素)、微量的二氧化碳、三氧化硫以及多种维生素(VB1、VB2、VB6、VB12、Vc、Vh、Vp等)和各种氨基酸。');
insert  into `commodity`(`id`,`class_id`,`title`,`price`,`specifications`,`place`,`alcohol`,`photo`,`content`) values (3,2,'长城三星干红葡萄酒','45.00','750ml/瓶','河北省怀来县沙城镇','12%vol','red_wine.jpg','公司现拥有国际评酒委员2名、国家评酒委员2名，具有大专以上学历的专业技术人员占全员的76%，全员文化、专业素质居全国行业前列。\r\n为使酿造前沿技术第一时间转化为生产力，公司加快与国内高等院校（西北农林科技大学、中国农业大学、江南大学等）联合的步伐，合作研究了多项建设项目。2002年申报了 “五万吨高档干红葡萄酒加工扩建工程及葡萄与葡萄酒研发中心建设项目”、“科技创新型星火龙头企业”、“无公害葡萄生产关键技术集成与产业化示范”、“AOC级酿酒葡萄原产地项目研究—赤霞珠”等省级、国家级重大科研项目，并已被列入国家重点开发和扶持项目。2004年，与中国农业大学组建了首家葡萄酒研发中心—中粮集团-中国农大长城葡萄酒科技研发中心，并于2005年11月，完成了“红葡萄酒中酚类物质HPLC-MS谱库构建及指纹分析”项目，通过国际教育部鉴定验收，达到国际领先水平,该项目的完成，为长城品牌产品质量的均衡和产品等级划分打下基础，同时为中国葡萄酒质量标准的制定提供了依据， 2006年，华夏公司应用该科研成果申报了国家星火计划--“优质干红葡萄酒酚类物质纹库控制技术研究”项目，2007-2008年，针对《红葡萄酒中酚类物质谱库构建及指纹分析技术推广应用》进行了续研。多年来，华夏长城始终进行着葡萄园的管理创新，为更好的探索先进的葡园管理模式，确保长城品牌的卓越品质，2006年，华夏公司再从法国引进7个品种，19个品系的优质名种酿酒葡萄苗木，建立母本园，改变传统的篱架势为水平龙干式，提高酿酒葡萄的质量，以实现葡萄生产的良种化、区域化、多样化。2007年，华夏公司自主完成《电渗析法在葡萄酒冷稳定处理中的应用研究》与《新型葡萄酒酿造工艺的研究》项目研究，通过河北省科技厅鉴定验收，达到了国际先进水平，同年与中国农业大学共同承担了国家重点“十一·五”规划—948项目，进行酿酒葡萄品种、品系选育。2008年6月《特级珍酿葡萄酒创新工艺技术研究》项目，获得秦皇岛市科技进步一等奖、河北省科技进步三等奖。');
insert  into `commodity`(`id`,`class_id`,`title`,`price`,`specifications`,`place`,`alcohol`,`photo`,`content`) values (4,2,'长城华夏九二干红葡萄酒','160.00','750ml/瓶','河北省怀来县沙城镇','13.5%vol','red_wine.jpg','公司现拥有国际评酒委员2名、国家评酒委员2名，具有大专以上学历的专业技术人员占全员的76%，全员文化、专业素质居全国行业前列。\r\n为使酿造前沿技术第一时间转化为生产力，公司加快与国内高等院校（西北农林科技大学、中国农业大学、江南大学等）联合的步伐，合作研究了多项建设项目。2002年申报了 “五万吨高档干红葡萄酒加工扩建工程及葡萄与葡萄酒研发中心建设项目”、“科技创新型星火龙头企业”、“无公害葡萄生产关键技术集成与产业化示范”、“AOC级酿酒葡萄原产地项目研究—赤霞珠”等省级、国家级重大科研项目，并已被列入国家重点开发和扶持项目。2004年，与中国农业大学组建了首家葡萄酒研发中心—中粮集团-中国农大长城葡萄酒科技研发中心，并于2005年11月，完成了“红葡萄酒中酚类物质HPLC-MS谱库构建及指纹分析”项目，通过国际教育部鉴定验收，达到国际领先水平,该项目的完成，为长城品牌产品质量的均衡和产品等级划分打下基础，同时为中国葡萄酒质量标准的制定提供了依据， 2006年，华夏公司应用该科研成果申报了国家星火计划--“优质干红葡萄酒酚类物质纹库控制技术研究”项目，2007-2008年，针对《红葡萄酒中酚类物质谱库构建及指纹分析技术推广应用》进行了续研。多年来，华夏长城始终进行着葡萄园的管理创新，为更好的探索先进的葡园管理模式，确保长城品牌的卓越品质，2006年，华夏公司再从法国引进7个品种，19个品系的优质名种酿酒葡萄苗木，建立母本园，改变传统的篱架势为水平龙干式，提高酿酒葡萄的质量，以实现葡萄生产的良种化、区域化、多样化。2007年，华夏公司自主完成《电渗析法在葡萄酒冷稳定处理中的应用研究》与《新型葡萄酒酿造工艺的研究》项目研究，通过河北省科技厅鉴定验收，达到了国际先进水平，同年与中国农业大学共同承担了国家重点“十一·五”规划—948项目，进行酿酒葡萄品种、品系选育。2008年6月《特级珍酿葡萄酒创新工艺技术研究》项目，获得秦皇岛市科技进步一等奖、河北省科技进步三等奖。');
insert  into `commodity`(`id`,`class_id`,`title`,`price`,`specifications`,`place`,`alcohol`,`photo`,`content`) values (5,4,'拉菲雾禾山谷干红葡萄酒','79.00','750ml/瓶','法国','13%vol','red_wine.jpg','浪漫的法国国度，坐落着大大小小的葡萄庄园，盛产着各种各样的葡萄酒，而位居五大名庄第一的拉菲庄园，是世界顶级红酒酒庄，拉菲庄园的盛名及其出产拉菲红酒的品质声誉一直维持至今，十分受国民喜爱，法国进口的拉菲红酒独具特色。\r\n\r\n2014年，天气气候稳定，土壤湿润肥沃，葡萄植株得以自然生长，充分吸收大地养分，出落成丰硕晶莹的葡萄果实。拉菲庄园的酿造师们运用传统工艺与现代技艺相结合，精心酿造每一支葡萄酒，致力打造出顶级的葡萄酒。严苛的管理条件，细致的采摘与挑拣，使得2-3棵葡萄树才能产一瓶拉菲红酒，这充分保证了2014年拉菲红酒的品质。\r\n\r\n2014年法国进口的拉菲红酒，独具特色，充满个性，口感柔软飘逸，单宁厚度十足，酒色澄澈，酒果味丰富而顺滑，深受到国民的喜爱。');
insert  into `commodity`(`id`,`class_id`,`title`,`price`,`specifications`,`place`,`alcohol`,`photo`,`content`) values (6,4,'智利原瓶红酒巴斯克花园葡萄酒','259.00','750ml/瓶','法国','14%vol','red_wine.jpg','拉菲红酒能拥有世界顶级的优秀品质，当然首先是拉菲庄园的土壤及所处地方微型气候(Micro Climate)得天独厚。拉菲庄园总面积90公顷，每公顷种植八千五百棵葡萄树。其中卡本妮苏维翁（国内译名：赤霞珠）(Cabernet Sauvignon)占70%左右，梅洛(Merlot)占20%左右，其余为卡本妮弗朗克(Cabernet Franc)。平均树龄在四十年以上。每年的产量大约三万箱酒(每箱12支750ml算)。此产量居所有世界顶级名庄之冠。以此产量及其能维持的价格相比，拉菲庄的成就真是无人能及。拉菲庄园的葡萄种植采用非常传统的方法，基本不使用化学药物和肥料，以小心的人工呵护法，让葡萄完全成熟才采摘。在采摘时熟练的工人会对葡萄进行树上采摘筛选，不好不采。葡萄采摘后送进压榨前会被更高级的技术工人进行二次筛选，确保被压榨的每粒葡萄都达高质要求。\r\n拉菲红酒\r\n拉菲红酒(5张)\r\n在拉菲每2至3棵葡萄树才能生产一瓶750ml的酒。为了保护这些矜贵的葡萄树，如没有总公司的特约，拉菲庄一般是不允许别人参观的。除此之外，拉菲庄还是出名愿花重本雇用最顶级酿酒大师的名庄。拉菲酒的个性温柔婉细，较为内向，不像同产于菩依乐村的两大名庄拉图和武当王的刚强个性。拉菲的花香、果香突出，芳醇柔顺，所以很多葡萄酒爱好者称拉菲为葡萄酒王国中的“皇后”。\r\n除拉菲外，罗斯柴尔德家族在波尔多、智利、意大利、葡萄牙以及希腊均拥有不少的优质名园和葡萄酒品牌。其中饮家较为熟知的波尔多名庄有宝物隆的“依云卓”L\'Evangile “杜哈米雍”Ch. Duhart Milon，拉菲的副牌酒“小拉菲”Carruades de Lafite和都夏美隆副牌“杜哈磨坊”Moulin de Duhart，而希腊亚历山大干红“Alexander dry red wine”则是在希腊饮用最多的红酒。');
insert  into `commodity`(`id`,`class_id`,`title`,`price`,`specifications`,`place`,`alcohol`,`photo`,`content`) values (7,4,'法国路易拉菲红酒','59.00','750ml/瓶','法国','12%vol','red_wine.jpg','拉菲(Lafite)酒庄，作为法国波尔多五大名庄之一，有着悠久的历史。1354年，创园于菩依乐村。拉菲酒的花香、果香突出，芳醇柔顺，十分典雅，被称为葡萄酒王国中的“皇后”。虽然历经几个世纪的变迁，拉菲酒庄一直持守着虔诚的酿酒精神和严苛的工艺标准，把拉菲红酒作为世界顶级葡萄酒的质量和声誉维持至今。\r\n1354年，拉菲(Lafite)酒庄创园于菩依乐村。\r\n在14世纪，拉菲酒庄就已经相当有名，其产品更是凡尔赛宫贵族们的杯中佳酿。\r\n17世纪，法国上至皇宫贵族，下至平民百姓都基本饮用勃艮第的葡萄酒，而当时法王路易十五的情妇庞巴迪却对拉菲情有独钟，自此拉菲成为巴黎凡尔赛宫贵族们的杯中佳物。18世纪初拉菲进入英国市场之后，不久就受到英国众多识酒人士的收藏之物，1732-1733年期间，英国第一任首相罗伯特·沃尔波（Robert.Walpole）平均每三个月就要购买一桶拉菲。\r\n1855年，法国政府对葡萄酒名庄进行了迄今为止唯一的一次评级，位列第一级的名庄有4个，而拉菲就排名第一。\r\n1985年伦敦佳士得拍卖会上，一瓶1787年带有时任美国总统的汤马士·杰弗逊(Thomas Jefferson)签名的拉菲以105,000英镑的天价由Forbes杂志老板Malcolm Forbes投得。因此，拉菲红酒至2012年仍保持着世界上最贵一瓶葡萄酒的记录。\r\n在拉菲酒庄，2-3棵葡萄树才能产一瓶红酒，整个酒庄年产量控制在2—3万箱(每箱12支，每支750ml)。由于供不应求，拉菲红酒的预订都是在葡萄成熟的半年前进行，而且每个客人最多只能预订20箱。而年代久远的拉菲红酒，更是存世稀少，因此受到红酒收藏家的狂热追捧。比如，在1985年伦敦佳士得拍卖会上，一瓶1787年的拉菲红酒以10.5万英镑的高价拍卖，创下并保持了迄今为止最昂贵葡萄酒的世界纪录与以往的顶级红酒拍卖不同。据了解，2012年5月推出的2008年份一箱12瓶装的拉菲庄红酒发行价为1950英磅，短短两个月就飚升至3500英磅。');

/*Table structure for table `commodity_class` */

DROP TABLE IF EXISTS `commodity_class`;

CREATE TABLE `commodity_class` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `title` varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='商品分类';

/*Data for the table `commodity_class` */

insert  into `commodity_class`(`id`,`title`) values (1,'张裕');
insert  into `commodity_class`(`id`,`title`) values (2,'长城');
insert  into `commodity_class`(`id`,`title`) values (3,'菲埃妮古堡');
insert  into `commodity_class`(`id`,`title`) values (4,'拉菲庄园');
insert  into `commodity_class`(`id`,`title`) values (5,'格拉芙庄园');
insert  into `commodity_class`(`id`,`title`) values (6,'澳塞诗');

/*Table structure for table `company_profile` */

DROP TABLE IF EXISTS `company_profile`;

CREATE TABLE `company_profile` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` text NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公司简介';

/*Data for the table `company_profile` */

insert  into `company_profile`(`id`,`content`) values (2,'在产品研发商，我们秉承\"质量优先、创意独特、工艺先进、做工精细、包装精美\"设计理念。生产的\"梅卡庄园\" 等系列品牌葡萄酒，引进国际先进的葡萄酒酿造设备，选用国际葡萄酒城原产地的高档葡萄酒原汁，聘用国内外著名的酿酒师和专业调酒师精心酿造而成。企业旗下有多款产品荣获国家专利，在国内外葡萄酒品评会上取得较好的口碑和成绩。庄园通过种植葡萄，葡萄酒酿造，葡萄酒灌装，自主品牌营销，为国内外消费者提供优质葡萄酒。 企业始终坚持以\"诚信、质量、创新、服务\"为宗旨。在生产销售中，注重品质优先，用最专注、专业的精神酿造最优质的葡萄酒；以克服逆境的勇气寻求产品差异化，讲究核心竞争力；用谦虚的态度认真努力打造市场成熟品牌，不同的酒类满足消费者的个性需求。企业以种植酿造为龙头，以销售为动力，依靠良好的信誉，优质的品牌，可靠的口碑，一流的管理服务赢得市场来创造葡萄酒界的美誉度。贯彻\"诚信铸就品牌，融合赢得市场\"的企业宗旨，与满怀热情的广大仁人志士一道，携手共赢。');

/*Table structure for table `information` */

DROP TABLE IF EXISTS `information`;

CREATE TABLE `information` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '资讯ID',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `author` varchar(30) NOT NULL COMMENT '作者',
  `content` text NOT NULL COMMENT '内容',
  `date` varchar(10) NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='公司资讯';

/*Data for the table `information` */

insert  into `information`(`id`,`title`,`author`,`content`,`date`) values (1,'全力备战2017春节销售季','路易十六','   春节临近葡萄酒市场迎来旺季，ChrosWine公司日平均发货量达10组以上（每组为3750标准箱）。为确保天津、上海、浙江等全国商超系统春节期间市场旺销，ChrosWine公司积极备货，在华润万家、麦德龙、苏果等大超市均布置了“ChrosWine”系列产品的堆头和端架，同时公司加大促销力度，针对各地实际情况，采取多种促销手段和方式，在营造春节气氛的同时，有效地拉动了销售的增长。\r\n \r\n春节来临王朝公司加班加点生产满足市场需求\r\n\r\n     至元月中旬，累计发往全国各地90余万箱，高峰时期仓储物流24小时收发货，确保产品及时供应的同时，实现公司产品销售良好开局。','2017-03-08');
insert  into `information`(`id`,`title`,`author`,`content`,`date`) values (2,'葡萄酒送礼也有讲究','路易十六','你是否有这样的烦恼？想选一款葡萄酒送礼，但却不知道该挑选什么样的葡萄酒才能显得有档次，这礼才能送的有价值。\r\n\r\n　　送领导 \r\n\r\n　　一直以来领导都是送礼的最大热门,也因为是最大热门所以该送的他都收到过了,很难再送什么了,葡萄酒要送的话就要送出品牌和品位,要让他看出来你多多少少是个懂酒的，因为送多了他自己就懂点，要不送得再贵也会被他小看。 \r\n\r\n　　法国的牌子在中国风行已久，但不是任何等级和牌子都好随便送的。AOC，或者是高级AOC很必要，但AOC和AOC价格可不一样，就看你会不会选了。 \r\n\r\n　　送长辈 \r\n\r\n　　送长辈，说穿了就是送健康。葡萄酒在心血管方面的功效是其他酒不能比的：英法两国的饮食习惯相似，所摄入脂肪量也差不多，但法国人患心血管疾病的比例明显低于英国，这就与法国人酷爱饮用红葡萄酒有关了。英国科学家发现葡萄皮中含有多酚物质，该物质可防止血管变窄。每天一杯红酒，对老人好处多多。如今的上海市场上，红酒销量早就超过传统白酒了，你可不要表错情哦。 \r\n\r\n　　至于口感方面，老人一般不喜欢口味复杂、单宁过重的葡萄酒，这是要注意的。 \r\n\r\n　　送女性 \r\n\r\n　　送女性朋友的话，首先要注重葡萄酒的外观是否好看，还有就是口味是否柔和悦人、酒精度不要太高。很多女性与其说是在喝酒，不如说是在喝感觉。一般来说，选葡萄酒送女性，品牌不是很重要，主要还是要取巧，象一些含有美乐、莎当妮、金粉黛尔的品种都很畅销。有些微度汽化的果酒风格也很好。 \r\n\r\n　　送酒客 \r\n\r\n　　如果你送的是“骨灰级”的酒客，当然是直冲他的心头好而去。一般资深酒客都能习惯旧世界酒的复杂，如果你还能同时送个冰桶或者醒酒器的话，那他们要乐死了，还会对你刮目相看，不是瞎说，真的是刮目后再看哦。如果是菜鸟，那建议还是先选一些低酒精度、好入口的红酒，那样比较安全，不会抱怨你送的又不好喝、又贵。','2017-07-09');
insert  into `information`(`id`,`title`,`author`,`content`,`date`) values (3,'葡萄酒送礼也有讲究','路易十三','你是否有这样的烦恼？想选一款葡萄酒送礼，但却不知道该挑选什么样的葡萄酒才能显得有档次，这礼才能送的有价值。\r\n\r\n　　送领导 \r\n\r\n　　一直以来领导都是送礼的最大热门,也因为是最大热门所以该送的他都收到过了,很难再送什么了,葡萄酒要送的话就要送出品牌和品位,要让他看出来你多多少少是个懂酒的，因为送多了他自己就懂点，要不送得再贵也会被他小看。 \r\n\r\n　　法国的牌子在中国风行已久，但不是任何等级和牌子都好随便送的。AOC，或者是高级AOC很必要，但AOC和AOC价格可不一样，就看你会不会选了。 \r\n\r\n　　送长辈 \r\n\r\n　　送长辈，说穿了就是送健康。葡萄酒在心血管方面的功效是其他酒不能比的：英法两国的饮食习惯相似，所摄入脂肪量也差不多，但法国人患心血管疾病的比例明显低于英国，这就与法国人酷爱饮用红葡萄酒有关了。英国科学家发现葡萄皮中含有多酚物质，该物质可防止血管变窄。每天一杯红酒，对老人好处多多。如今的上海市场上，红酒销量早就超过传统白酒了，你可不要表错情哦。 \r\n\r\n　　至于口感方面，老人一般不喜欢口味复杂、单宁过重的葡萄酒，这是要注意的。 \r\n\r\n　　送女性 \r\n\r\n　　送女性朋友的话，首先要注重葡萄酒的外观是否好看，还有就是口味是否柔和悦人、酒精度不要太高。很多女性与其说是在喝酒，不如说是在喝感觉。一般来说，选葡萄酒送女性，品牌不是很重要，主要还是要取巧，象一些含有美乐、莎当妮、金粉黛尔的品种都很畅销。有些微度汽化的果酒风格也很好。 \r\n\r\n　　送酒客 \r\n\r\n　　如果你送的是“骨灰级”的酒客，当然是直冲他的心头好而去。一般资深酒客都能习惯旧世界酒的复杂，如果你还能同时送个冰桶或者醒酒器的话，那他们要乐死了，还会对你刮目相看，不是瞎说，真的是刮目后再看哦。如果是菜鸟，那建议还是先选一些低酒精度、好入口的红酒，那样比较安全，不会抱怨你送的又不好喝、又贵。','2017-03-00');
insert  into `information`(`id`,`title`,`author`,`content`,`date`) values (6,'葡萄酒送礼也有讲究','路易十四','你是否有这样的烦恼？想选一款葡萄酒送礼，但却不知道该挑选什么样的葡萄酒才能显得有档次，这礼才能送的有价值。\r\n\r\n　　送领导 \r\n\r\n　　一直以来领导都是送礼的最大热门,也因为是最大热门所以该送的他都收到过了,很难再送什么了,葡萄酒要送的话就要送出品牌和品位,要让他看出来你多多少少是个懂酒的，因为送多了他自己就懂点，要不送得再贵也会被他小看。 \r\n\r\n　　法国的牌子在中国风行已久，但不是任何等级和牌子都好随便送的。AOC，或者是高级AOC很必要，但AOC和AOC价格可不一样，就看你会不会选了。 \r\n\r\n　　送长辈 \r\n\r\n　　送长辈，说穿了就是送健康。葡萄酒在心血管方面的功效是其他酒不能比的：英法两国的饮食习惯相似，所摄入脂肪量也差不多，但法国人患心血管疾病的比例明显低于英国，这就与法国人酷爱饮用红葡萄酒有关了。英国科学家发现葡萄皮中含有多酚物质，该物质可防止血管变窄。每天一杯红酒，对老人好处多多。如今的上海市场上，红酒销量早就超过传统白酒了，你可不要表错情哦。 \r\n\r\n　　至于口感方面，老人一般不喜欢口味复杂、单宁过重的葡萄酒，这是要注意的。 \r\n\r\n　　送女性 \r\n\r\n　　送女性朋友的话，首先要注重葡萄酒的外观是否好看，还有就是口味是否柔和悦人、酒精度不要太高。很多女性与其说是在喝酒，不如说是在喝感觉。一般来说，选葡萄酒送女性，品牌不是很重要，主要还是要取巧，象一些含有美乐、莎当妮、金粉黛尔的品种都很畅销。有些微度汽化的果酒风格也很好。 \r\n\r\n　　送酒客 \r\n\r\n　　如果你送的是“骨灰级”的酒客，当然是直冲他的心头好而去。一般资深酒客都能习惯旧世界酒的复杂，如果你还能同时送个冰桶或者醒酒器的话，那他们要乐死了，还会对你刮目相看，不是瞎说，真的是刮目后再看哦。如果是菜鸟，那建议还是先选一些低酒精度、好入口的红酒，那样比较安全，不会抱怨你送的又不好喝、又贵。','2017-09-29');
insert  into `information`(`id`,`title`,`author`,`content`,`date`) values (7,'ChrosWine大奖赛','路易十九',' 六月的北京城“高烧不断”，但有一件惬意的事情让人心情舒畅。\r\n在雾岚山下、葡萄园旁鉴赏波龙堡美酒，传递大奖赛的精神，与房山区有关领导一起探讨大奖赛分会场合作事宜。\r\n\r\n    2017年6月15日下午，海淀区委常委、宣传部长陈名杰带领大奖赛执行委员会的成员们，来到位于房山区八十亩地村的波龙堡酒庄进行考察调研活动，并与房山区委常委、宣传部长曹蕾一行人就在房山区设立分会场以及大奖赛期间酒庄考察等活动进行座谈。\r\n\r\n    房山区在去年，成功举办了首届“一带一路”国际葡萄酒大赛。房山区与著名的葡萄酒产区法国波尔多具有相同的纬度，在种植酿酒葡萄上具有得天独厚的地理资源优势。房山区目前已经设立64家酒庄，种植面积2万余亩，是推动中国葡萄酒产业的重要产区。\r\n\r\n波龙堡葡萄种植基地\r\n\r\n    经过两区代表的交流，大家一致认为，2018 CMB·Haidian大奖赛不只是海淀区的一件大事，作为一个国际性平台，应该汇聚各方资源推动联合发展，进行市场化运作，突破活动壁垒，实现多方合作共赢。海淀区、房山区要以本次大奖赛为契机和突破点，持续不断推进文商旅产业协同发展，将大奖赛设计规划全面落地，形成发展大势，推动教育、文创等相关产业齐头发展。\r\n\r\n    两区代表就大奖赛赛前、赛中、赛后的宣传和参观接待等事宜进行了讨论。房山区葡萄酒促进中心将与海淀区大奖赛执委会办公室密切合作，共同推进大奖赛落地的具体内容。\r\n\r\n    执委办一行还在庄主唐捷女士的带领下参观了有着“中国有机红酒第一酒庄”称号的波龙堡酒庄，分别从种植、酿造、酒庄历史和文化等方面了解中国的有机葡萄酒产业。\r\n\r\n参观葡萄田\r\n\r\n\r\n种植在葡萄藤中间具有固氮作用的花生\r\n\r\n\r\n收获季节的波龙堡\r\n\r\n（为保证采摘时葡萄的品质，酒庄采摘只在傍晚进行）\r\n\r\n\r\n    北京波龙堡酒庄坚持有机生产理念，从2005年至今相继获得了中国有机、欧盟有机、美国有机的认证。诚信、敬业、爱国、友善是波龙堡的经营理念，对于品质的不断追求也得到了行业顶级赛事的认可。\r\n\r\n——今年4月份，在英国伦敦举办的2017 Decanter世界葡萄酒大赛（DWWA）上，波龙堡选送的两款酒“波龙堡2014珍藏有机干红葡萄酒”和“波龙堡2015有机干白葡萄酒”均斩获大赛银奖。\r\n\r\n\r\n波龙堡酒庄酒窖\r\n\r\n\r\n    相信在明年海淀举办的2018CMB大奖赛上波龙堡将再创佳绩，推动Made in China成为品质葡萄酒的代名词！','2017-06-01');

/*Table structure for table `knowledge` */

DROP TABLE IF EXISTS `knowledge`;

CREATE TABLE `knowledge` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `author` varchar(30) NOT NULL COMMENT '作者',
  `content` text NOT NULL COMMENT '内容',
  `date` varchar(10) NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='红酒知识';

/*Data for the table `knowledge` */

insert  into `knowledge`(`id`,`title`,`author`,`content`,`date`) values (1,'全球主流红葡萄酒：赤霞珠VS黑皮诺','路易十六','赤霞珠（CabernetSauvignon）与黑皮诺（Pinot Noir）在红葡萄酒界有着“王”与“后”的美誉，两者在风格上各具特色，并各自得到世界各地众多葡萄酒爱好者的追捧，本文将以对比的方式为您综合介绍这两种葡萄酒。\r\n \r\n一、 小档案\r\n赤霞珠\r\n\r\n起源地：法国波尔多（赤霞珠实际上是由品丽珠和长相思在17世纪时杂交而成。）\r\n种植面积：65万亩\r\n \r\n黑皮诺\r\n\r\n起源地：法国勃艮第（黑皮诺是一种需要葡萄种植者和酿酒师精心照顾的葡萄品种。）\r\n种植面积：29万亩\r\n \r\n二、风味\r\n赤霞珠\r\n赤霞珠在不同的产区有不同的风格。尽管原产于法国波尔多，但赤霞珠在如美国加州、华盛顿、智利和澳大利亚等气候温暖产区也能生长良好。\r\n在以法国为代表的旧世界，通常赤霞珠都会和梅洛和品丽珠等进行混酿，在很多其他产区也会采用这样的“波尔多混酿”（Bordeaux Blend）的经典组合，酿制出带有黑醋栗、梅子、黑樱桃以及摩卡咖啡风味的葡萄酒。尽管赤霞珠天生能酿制出酒体丰满的葡萄酒，但波尔多赤霞珠葡萄酒却常被贴上“雅致”的标签。\r\n与旧世界相比，新世界所产的赤霞珠通常果味更浓郁。酒中较常见的风味有黑樱桃、甘草和黑胡椒味，还伴有阵阵香草味。此外，新世界赤霞珠葡萄酒的单宁和酸度都更低，但酒精度更高。\r\n \r\n黑皮诺\r\n黑皮诺葡萄酒通常呈宝石红色，带有红色浆果、鲜花、土壤和蘑菇的香气，酒体介于清淡和适中之间，酸度明显。黑皮诺非常善变，因产区和年份的不同，它会表现出迥然不同的风味。由于黑皮诺葡萄酒中的单宁通常不会太高，其丝滑口感和精致风味因此而得以进一步突出，极具优雅魅力，并由此成为很多白葡萄酒爱好者最能接受的红葡萄酒。\r\n以黑皮诺酿制、最具名望的酒品是法国勃艮第红葡萄酒，这种酒多带有精致的红色水果风味，丝滑柔顺的口感，还散发着与众不同的土壤气息。新世界的黑皮诺葡萄酒一般带有浓郁而直接的红色浆果风味，如覆盆子、草莓和樱桃等鲜果味道。','2017-03-02');
insert  into `knowledge`(`id`,`title`,`author`,`content`,`date`) values (2,'霞多丽葡萄酒指南','路易十六','对于霞多丽葡萄酒的爱好者来说，生活中可以没有香奈儿，却不可以缺少霞多丽。凭借极强的适应能力，霞多丽在全球的不同产区都广泛种植，并呈现多样风格，本文就将带您走近这一拥有全球最多粉丝的白葡萄品种。\r\n \r\n四、为什么有些含有奶油味？\r\n香草、黄油、椰子和莳萝都是橡木桶陈酿赋予霞多丽葡萄酒香味，这些风味经过特殊的发酵后往往会变成一种油腻的奶香，而且口感柔滑。\r\n \r\n五、佐餐搭配\r\n未经橡木桶陈酿的霞多丽葡萄酒风味与灰皮诺和长相思葡萄酒很相似，但其青苹果风味相对轻一些。这主要取决于采摘时葡萄的成熟度。如果所使用的酿酒葡萄不是非常成熟，霞多丽葡萄酒会有柠檬味和青苹果味，如果葡萄足够成熟，霞多丽葡萄酒会有菠萝和无花果的风味。经橡木桶陈酿的霞多丽葡萄酒酒体丰满，带有橡木桶赋予的香草味、黄油味甚至焦糖味。在凉爽气候条件下酿成的带有黄油风味的霞多丽葡萄酒含有更明显的柑橘味，而在温暖气候条件下酿成的霞多丽则带有更多热带水果风味。\r\n \r\n1. 肉类\r\n鸡胸肉、火鸡胸肉、猪大排骨、大比目鱼、鲑鱼、鳕鱼、鲟鱼、油薄皮鱼、大西洋鲑鱼、龙虾、螃蟹、扇贝、虾、蛤蚌、牡蛎等。\r\n \r\n2. 香料\r\n龙嵩叶、荷兰芹、百里香、柠檬皮屑、墨角兰、白胡椒、青葱、家禽佐料等香料或香草类。\r\n \r\n3. 奶酪\r\n柔软-半柔软的牛乳奶酪和山羊奶酪。\r\n \r\n4. 蔬菜\r\n黄色南瓜、豌豆、西葫芦、芦笋、白蘑菇、松露、杏仁。','2017-03-03');
insert  into `knowledge`(`id`,`title`,`author`,`content`,`date`) values (4,'长相思葡萄酒佐餐指南','路易十六','  长相思(SauvignonBlanc)葡萄原产于波尔多，是一种香气浓郁的白葡萄品种，通常带有突出的青涩果香和像醋栗、灯笼椒、芦笋等植物的芬香，以它所酿成的葡萄酒酸度高，酒体适中，以青草等草本风味见长，在佐餐时能够与绿色蔬菜相得益彰。若是考虑香料元素，欧芹、迷迭香、罗勒、香菜和薄荷都能与其创造理想效果。\r\n       另外，在卢瓦尔河谷，长相思葡萄酒还有一个十分经典的搭配。在靠近桑塞尔Crottin de Chavignol地区出产一种享誉世界的奶酪——克罗汀（Crottin）奶酪，该奶酪口感圆润、余味绵长，被称为长相思的绝配。\r\n\r\n 1、与肉类搭配：\r\n       白肉：鸡肉、猪排和火鸡。\r\n       鱼：罗非鱼、鲈鱼、鳕鱼、鲑鱼、大比目鱼、鲷鱼、蚌类、龙虾和蛤等。\r\n\r\n 2、与香料、绿色草本搭配：\r\n       香料：白胡椒粉、香菜、茴香、姜黄、藏红花等。\r\n       绿色草本：欧芹、紫苏、薄荷、龙蒿、百里香、香葱、莳萝、迷迭香等。\r\n\r\n 3、与奶酪搭配：\r\n       口感柔软，口味酸咸的奶酪，例如羊奶奶酪、酸奶酪和法式酸奶油等。\r\n\r\n 4、与蔬果类搭配：\r\n      果肉厚实的果蔬更能体现长相思的清爽酸度，这类食物有芦笋乳蛋饼、黄瓜莳萝酸奶沙拉、绿豆沙和白豆角炖配西葫芦等。','2017-03-00');
insert  into `knowledge`(`id`,`title`,`author`,`content`,`date`) values (5,'杯酒人生之黑皮诺','路易十六','说实话，对于喜欢葡萄酒的人士来说，葡萄酒的世界略显沉闷。当你坐在座位上，悠闲的闻着杯中的来自法国梅多克的葡萄酒，心情愉悦，想要与人分享，但是身边的这位朋友却只对梅多克的地理感兴趣，你会不会很郁闷。事实就是这样，想要与人分享品尝葡萄酒的乐趣很难，找到志同道合的人更难。但是，不管你信不信，葡萄酒并不是一种普通的饮品，她能够在你品尝的第一口直击内心。\r\n既然葡萄酒的世界依旧那么高傲，那么我们可以尝试改变一下交流方式。今天我们以世界上最受欢迎的葡萄酒之一，黑皮诺作为例子来介绍。希望这些信息能够有助于在朋友圈打开话题，甚至惊艳四座。\r\n黑皮诺源自法国，对气候非常挑剔，相较其他，也确实不易酿出好葡萄酒，它“情绪”十分不稳定，但事实上如果成功酿造的话，是颇具淑女风范、拥有梦幻般神奇魅力的一款葡萄酒。\r\n今年夏天，就在朋友圈中刮起一阵黑皮诺的清爽之风吧！\r\n1、酒体较轻\r\n\r\n黑比诺如同上海作女，是有味道有内涵的“作法”，赏识和爱它的人会被它迷得五迷三道。试着以一张白纸作为背景观察一杯黑皮诺，你会发现她的颜色像稀释的血液，带有一种勾摄魂魄的魅力。\r\n2、果皮薄\r\n\r\n黑皮诺在生长的过程中，每一天都充满挑战。因为不像赤霞珠还有仙粉黛，黑皮诺的果皮很薄，十分敏感，抗病性弱，环境的变化对于黑皮诺来说非常明显。\r\n3、果香浓郁\r\n\r\n樱桃、草莓、覆盆子、黑莓，有时也会有蔓越莓的味道。任意一杯黑皮诺，都是果香浓郁。','2017-03-00');

/*Table structure for table `recruit` */

DROP TABLE IF EXISTS `recruit`;

CREATE TABLE `recruit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(50) NOT NULL COMMENT '职位名称',
  `job_describe` varchar(100) NOT NULL COMMENT '职位描述',
  `requirement` varchar(100) NOT NULL COMMENT '招聘要求',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='招贤纳士';

/*Data for the table `recruit` */

insert  into `recruit`(`id`,`title`,`job_describe`,`requirement`) values (1,'高管','本公司即将设立上海办事处，招聘活动区负责人一名，负责拓展华东地区业务',' 具有良好的销售和团队管理理念，三年以上销售工作经验，一年以上销售经理经验工作责任心强、踏实上进');
insert  into `recruit`(`id`,`title`,`job_describe`,`requirement`) values (3,'红酒Sales','无责底薪6K+带薪培训+公司自有客户资源+晋升机会多+福利多+公司氛围好+五险一金','大专以上学历but，颜值高于一切，你懂得！\r\n只要你具备以上条件，恭喜你，你是我们想要的人。');
insert  into `recruit`(`id`,`title`,`job_describe`,`requirement`) values (4,'调酒师学徒','协助调酒师处理日常事务，了解(洋酒、红酒、鸡尾酒)调制方法','18至28周岁，身体健康，踏实肯干。有无经验均可，能适应晚班。');
insert  into `recruit`(`id`,`title`,`job_describe`,`requirement`) values (5,'高薪销售','热爱销售，具备一定的销售能力,勇于证明自己，对销售行业有一定的兴趣与认识；','依托公司优质客户资源通过公司外呼系统/微信群，通过电话/网络的方式让客户了解我们的产品并掌握客户需求；为客户提供快速、准确、专业的销售及咨询服务。\r\n');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
