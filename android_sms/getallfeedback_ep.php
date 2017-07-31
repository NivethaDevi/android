<?php
include './DbHandler.php';

$db = new DbHandler();
//Get JSON posted by Android Application
$json = $_POST["usersJSON"];
//echo "$json";
//Remove Slashes
if (get_magic_quotes_gpc()){
$json = stripslashes($json);
}
//Decode JSON into an Array
$data = json_decode($json);
//Util arrays to create response JSON
$a=array();
//echo $a;
$b=array();
//echo $b;
//Loop through an Array and insert data read from JSON into MySQL DB
$db->getAllFeedbacks_ep($data[0]->pincode);

/*for($i=0; $i<count($data) ; $i++)
{
//Store User into MySQL DB
$res = $db->storeUser($data[$i]->userId,$data[$i]->field6,$data[$i]->field7,$data[$i]->field8,$data[$i]->field9,$data[$i]->field10,$data[$i]->field11,$data[$i]->field12,$data[$i]->field13,$data[$i]->field14,$data[$i]->field15,$data[$i]->field16,$data[$i]->field17,$data[$i]->field18,$data[$i]->field19,$data[$i]->field20,$data[$i]->field21,$data[$i]->field22,$data[$i]->field23,$data[$i]->field24,$data[$i]->field25,$data[$i]->field26,$data[$i]->field27,$data[$i]->field28,$data[$i]->field29,$data[$i]->field30,$data[$i]->field31,$data[$i]->field32,$data[$i]->field33,$data[$i]->field34,$data[$i]->field35,$data[$i]->field36,$data[$i]->field37,$data[$i]->field38,$data[$i]->field39,$data[$i]->field40,$data[$i]->field41,$data[$i]->field42,$data[$i]->field43,$data[$i]->field44,$data[$i]->field45,$data[$i]->field46,$data[$i]->field47,$data[$i]->field48,$data[$i]->field49,$data[$i]->field50);
	//Based on inserttion, create JSON response
	if($res){
		$b["id"] = $data[$i]->userId;
		$b["status"] = 'yes';
		array_push($a,$b);
	}else{
		$b["id"] = $data[$i]->userId;
		$b["status"] = 'no';
		array_push($a,$b);
	}
}*/
//Post JSON response back to Android Application
?>
