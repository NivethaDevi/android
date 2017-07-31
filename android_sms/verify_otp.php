<?php
 
include './DbHandler.php';
$db = new DbHandler();
$json = $_POST["usersJSON"];

//echo $json; 

if (get_magic_quotes_gpc()){

$json = stripslashes($json);

}

$data = json_decode($json);

$otp=$data[0]->field5; 
 
$response = array();
$response["error"] = false;
 
if ($otp && $otp != '') {
   // $otp = $_POST['otp'];
 
 
    $user = $db->activateUser($otp);
 
    if ($user != NULL) {
 
        $response["message"] = "User created successfully!";
        $response["profile"] = $user;
    } else {
        $response["message"] = "Sorry! Failed to create your account.";
    }
     
     
} else {
    $response["message"] = "Sorry! OTP is missing.";
}
 
 
echo json_encode($response);
?>
