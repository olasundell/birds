package se.atrosys.birds.flickr;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/10/11
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "rsp")
public class FlickrResponseModel {
	@XmlAttribute
	String stat;
	@XmlElement(name = "photos")
	FlickrPhotoList photos;
	
	public FlickrResponseModel() {
		photos = new FlickrPhotoList();
	}

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	<photos page="1" pages="5" perpage="100" total="451">
		<photo id="6472803225" owner="8751400@N06" secret="5b11a7034c" server="7165" farm="8" title="39 P (100)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6055084229" owner="94137765@N00" secret="abc30300b0" server="6082" farm="7" title="White-Faced Ducks (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6047458517" owner="13861029@N00" secret="74739feccb" server="6203" farm="7" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6041494624" owner="94137765@N00" secret="d8de3994fc" server="6062" farm="7" title="Dedication to Gerda and Willie" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5970521912" owner="45048567@N05" secret="5c1921d09c" server="6141" farm="7" title="Dendrocygna Viduata sketches" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5951302700" owner="87719028@N00" secret="8a5fcfe9be" server="6147" farm="7" title="With pointed fingers" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5815379974" owner="94137765@N00" secret="e23997d974" server="5235" farm="6" title="Ich liebe Dich" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5814293354" owner="60263124@N00" secret="9b9abf4828" server="3087" farm="4" title="white-faced whistling duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5761856966" owner="94137765@N00" secret="85189bf0a1" server="3442" farm="4" title="White-Faced Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5729412170" owner="94137765@N00" secret="3a433c8ab0" server="3561" farm="4" title="White-Faced Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5640117590" owner="48226631@N04" secret="2dd9da2829" server="5265" farm="6" title="White-faced ducks in the dusk" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5461094531" owner="14940438@N02" secret="76ec767420" server="5298" farm="6" title="_" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5317008285" owner="34633466@N03" secret="39c9fda21a" server="5281" farm="6" title="White-faced  / Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5240248496" owner="87719028@N00" secret="06030b2085" server="5089" farm="6" title="Water ballet" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5238598902" owner="87719028@N00" secret="b43c8a0be0" server="5083" farm="6" title="I've been running for my life . . ." ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5219875168" owner="73431654@N00" secret="6bc50830bf" server="4084" farm="5" title="Irerê, White-faced Whistling-Duck (Dendrocygna viduata) - Asa-Branca, Black-bellied Whistling-Duck (Dendrocygna autumnalis) - Marreca-Caneleira, Fulvous Whistling-Duck (Dendrocygna bicolor)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6413219813" owner="72243477@N00" secret="113857f89c" server="7148" farm="8" title="Wandering Whistling-ducks" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6408762327" owner="64905385@N07" secret="36dc4aebb3" server="6237" farm="7" title="Irerê (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6376381207" owner="69012796@N00" secret="310cbe23c3" server="6220" farm="7" title="White-faced duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6328792905" owner="24201429@N04" secret="f35ed0cab7" server="6230" farm="7" title="Dendrocygna viduata ( familia )" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6292610370" owner="16176711@N02" secret="1c18cb01be" server="6116" farm="7" title="White-faced whistling ducks (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6292343390" owner="13873063@N07" secret="8e9af4cbe5" server="6220" farm="7" title="Dendrocygna viduata" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6291823099" owner="13873063@N07" secret="ec199b06ef" server="6104" farm="7" title="Dendrocygna viduata" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6287392261" owner="12694900@N08" secret="3371e2aac2" server="6212" farm="7" title="Yaguaso cariblanco [White-faced Whistling-Duck] (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6283811887" owner="93372558@N00" secret="d7d406b620" server="6033" farm="7" title="White-faced Whistling-Duck, Liwonde (Malawi), 27-May-11" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6266539750" owner="8751400@N06" secret="64c4a4d47a" server="6057" farm="7" title="38P (276)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6227456409" owner="52125584@N07" secret="80c0546677" server="6211" farm="7" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6221772187" owner="94137765@N00" secret="8b67e96f0a" server="6168" farm="7" title="White-faced Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6211841849" owner="24201429@N04" secret="c43e8a4361" server="6219" farm="7" title="Dendrocygna viduata - WHITE-FACED WHISTLING-DUCK" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6205346016" owner="92184044@N00" secret="08f58da5b8" server="6176" farm="7" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6177552825" owner="55031801@N02" secret="87bcf90ae5" server="6177" farm="7" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6167692757" owner="62119764@N08" secret="f2dea8894b" server="6177" farm="7" title="Whistling Ducks" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6167498615" owner="59429971@N06" secret="40d46d9f16" server="6176" farm="7" title="White-faced Whistling Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6157780196" owner="59429971@N06" secret="8b5c161a12" server="6088" farm="7" title="White-faced Whistling Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6147579758" owner="52608057@N08" secret="9c69a69fd6" server="6067" farm="7" title="Ange is a chick magnet" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6147576638" owner="52608057@N08" secret="9de5a7e19e" server="6064" farm="7" title="A duck's eye view" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6147028595" owner="52608057@N08" secret="277c146c39" server="6205" farm="7" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6147028113" owner="52608057@N08" secret="250962c180" server="6160" farm="7" title="I'm ready for my close-up now!" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6147027827" owner="52608057@N08" secret="e4f5fb7a95" server="6187" farm="7" title="Ange and her birds" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6147024555" owner="52608057@N08" secret="9823830eca" server="6196" farm="7" title="Three of a Kind" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6091666046" owner="47222753@N04" secret="d7c96c2135" server="6191" farm="7" title="Irerê (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6081979901" owner="15745225@N00" secret="ed0e305378" server="6181" farm="7" title="White-faced Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6079071688" owner="51025921@N00" secret="b348fcde56" server="6187" farm="7" title="Ducks and Birds, May 2011" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6079063424" owner="51025921@N00" secret="251d59a703" server="6075" farm="7" title="White-faced Whistling Ducks" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6079061314" owner="51025921@N00" secret="143d870e6d" server="6068" farm="7" title="White-faced Whistling Ducks" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6042336454" owner="55031801@N02" secret="b5ecbe330f" server="6185" farm="7" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6014903446" owner="50053504@N04" secret="0329aa0287" server="6148" farm="7" title="White-faced Whistling Duckling" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6014900762" owner="50053504@N04" secret="8a4247ea06" server="6125" farm="7" title="White-faced Whistling Duck family" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="6009118610" owner="8637505@N08" secret="0e2af4365b" server="6002" farm="7" title="Dendrocygna viduata (White-Faced Whistling-Duck; Witwenpfeifgans)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5975894482" owner="36094100@N00" secret="43c18ef658" server="6133" farm="7" title="White-faced whistling duck family" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5944156915" owner="74137717@N00" secret="936050e6c4" server="6024" farm="7" title="Dona Irerê e seus irerezinhos no rumo das flores" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5927351283" owner="50053504@N04" secret="82e7373211" server="6129" farm="7" title="White-faced Whistling Ducks" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5926583071" owner="9403463@N05" secret="1c90786602" server="6008" farm="7" title="White-faced Whistling-cuck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5926583273" owner="9403463@N05" secret="d97c09772d" server="6004" farm="7" title="White-faced Whistling-cuck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5926583623" owner="9403463@N05" secret="269a11781f" server="6128" farm="7" title="White-faced Whistling-cuck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5902096242" owner="36917655@N08" secret="beddba0d3f" server="5200" farm="6" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5882453534" owner="93372558@N00" secret="f02191d780" server="5152" farm="6" title="White-faced Whistling-Duck, Lake Chilwa (Malawi), 15-May-11" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5875215747" owner="55031801@N02" secret="85c1b0681f" server="5073" farm="6" title="Irerê" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5825768529" owner="38956523@N06" secret="ff2ab6319c" server="3160" farm="4" title="0412 White-faced Whistling-Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5812827888" owner="48881750@N03" secret="b43ccfec66" server="5278" farm="6" title="Swimming Lesson" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5812254023" owner="48881750@N03" secret="99cd577b4a" server="5147" farm="6" title="Looking after the little one" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5778026778" owner="10541353@N00" secret="d62236f515" server="2486" farm="3" title="White Faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5735661572" owner="7457894@N04" secret="4ca193b2b4" server="2198" farm="3" title="NaBo11_d60_4662a" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5735661876" owner="7457894@N04" secret="c810c8881d" server="5110" farm="6" title="NaBo11_d60_4649a" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5735661790" owner="7457894@N04" secret="ff6a65eda1" server="5301" farm="6" title="NaBo11_d60_4659a" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5723250341" owner="8621390@N08" secret="76237b7f88" server="2635" farm="3" title="Irerê" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5715847203" owner="94137765@N00" secret="84ed7b2472" server="2472" farm="3" title="White-faced Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5656479214" owner="43581314@N08" secret="6999fff886" server="5224" farm="6" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5615797788" owner="31422972@N05" secret="f7742cde8c" server="5061" farm="6" title="Dendrocygna viduata (Irerê)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5610926645" owner="50615426@N06" secret="871c52a1a9" server="5028" farm="6" title="White-faced Whistling Ducks" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5574270953" owner="35534795@N07" secret="1b0779e1b3" server="5294" farm="6" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5519629614" owner="37007874@N00" secret="c900b18672" server="5093" farm="6" title="Dendrocygne veuf" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5516894487" owner="12530381@N07" secret="a88be1e754" server="5260" farm="6" title="The cutest quackers in the whole duckdom!" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5510270081" owner="22073371@N02" secret="67502c5f21" server="5052" farm="6" title="Marreca-Piadeira/Whistling-Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5500240439" owner="45813009@N08" secret="0f88f1ef5c" server="5173" farm="6" title="Irerê  -  Dendrocygna viduata" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5498970133" owner="32674493@N04" secret="35cfd3948d" server="5014" farm="6" title="Marreca-piadeira (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5494428312" owner="22073371@N02" secret="bf99262e6e" server="5171" farm="6" title="Marreca-piadeira/Whistling-Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5493832745" owner="22073371@N02" secret="8fdef8f91d" server="5093" farm="6" title="Marreca-piadeira/Whistling-Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5473340103" owner="33057724@N04" secret="b379ae8be8" server="5091" farm="6" title="White-faced Whistling Duck, Dendrocygna viduata" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5405368851" owner="48901350@N07" secret="457034ce61" server="5094" farm="6" title="Cocky" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5403938580" owner="30021718@N06" secret="3d89a0e1c1" server="5094" farm="6" title="Dendrocygna viduata, White-faced Whistling-Duck in Baringo" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5387328044" owner="16520061@N08" secret="0147b2393a" server="5219" farm="6" title="White-faced Whistling-Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5381867796" owner="44691276@N06" secret="b863bf4f1b" server="5044" farm="6" title="Dendrocygna viduata (White Faced Whistling Duck)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5380533953" owner="35534795@N07" secret="79e46eb114" server="5289" farm="6" title="White-faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5361866917" owner="55988100@N02" secret="e830bc35a8" server="5170" farm="6" title="Buscando refugio (Sirirí Pampa &quot;Dendrocygna viduata)&quot;" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5356084785" owner="94137765@N00" secret="600c810834" server="5007" farm="6" title="White-Faced Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5339267839" owner="94028658@N00" secret="b47ca5e39b" server="5007" farm="6" title="White Faced Whistling Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5338529145" owner="32674493@N04" secret="022ec5504e" server="5089" farm="6" title="Marreca-piadeira (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5329223579" owner="48901350@N07" secret="1d7bd2cdee" server="5009" farm="6" title="Whitey" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5300172046" owner="44338052@N08" secret="52266d6155" server="5242" farm="6" title="White-Faced Duck - Dendrocygna viduata" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5229345382" owner="47024491@N06" secret="00dc308428" server="5089" farm="6" title="White-faced Whistling-duck  (Dendrocygna viduata)  Irerê" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5219376061" owner="73431654@N00" secret="33af86fa44" server="5125" farm="6" title="Irerê, White-faced Whistling-Duck (Dendrocygna viduata)" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5186044651" owner="47200218@N02" secret="2d38f81422" server="4090" farm="5" title="White-faced Whistling Duck &quot;Dendrocygna viduata&quot;" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5182302363" owner="86419914@N00" secret="131da9a699" server="4106" farm="5" title="Dendrocygna viduata - Witwenpfeifgans im Allwetterzoo Münster" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5161217218" owner="48226631@N04" secret="7891ccf8b8" server="1404" farm="2" title="White-Faced Duck" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5142982085" owner="49819018@N06" secret="2731b14243" server="1148" farm="2" title="White-faced whistling ducks" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5142971529" owner="49819018@N06" secret="62abb1003f" server="4059" farm="5" title="White-headed whistling ducks in flight" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5142309931" owner="51482185@N07" secret="318efe8de1" server="4060" farm="5" title="Irerê" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5121464587" owner="74137717@N00" secret="1712f73aaf" server="4030" farm="5" title="Dendrocygna viduata" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5113997170" owner="31468595@N06" secret="8edb86d757" server="1198" farm="2" title="White-faced Wistling-duck (Dendrocygna viduata) Murchison falls&quot; NP Uganda" ispublic="1" isfriend="0" isfamily="0" />
	</photos>
	</rsp>

	 */

	public FlickrPhotoList getPhotos() {
		return photos;
	}
}
