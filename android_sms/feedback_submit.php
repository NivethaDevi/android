<?php
//Create Object for DB_Functions clas
include_once './db_functions.php';
//Create Object for DB_Functions clas
$db = new DB_Functions();


//Get JSON posted by Android Application
$json = $_POST["usersJSON"];
//echo "$json";
//Remove Slashes
if (get_magic_quotes_gpc()){
$json = stripslashes($json);
}
//Decode JSON into an Array
$data = json_decode($json);
//echo $data[0]->feedback_image;
//echo $data[0]->customer_id;
//Util arrays to create response JSON
$a=array();
//echo $a;
$b=array();
//echo $b;
//Loop through an Array and insert data read from JSON into MySQL DB
for($i=0; $i<count($data) ; $i++)
{
$res=$db->storeFeedback($data[$i]->customer_id,$data[$i]->pincode,$data[$i]->feedback_image,$data[$i]->status);
//echo "hello";
//Store User into MySQL DB
	if($res){
		$b["error"] = False;
		$b["message"] = "Feedback Successfully Submitted";
		array_push($a,$b);
	}else{
		$b["error"] = True;
		$b["message"] = "Feedback Submission Failed";
		array_push($a,$b);
	}
}
//Post JSON response back to Android Application
echo json_encode($a);
?>
