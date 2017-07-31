<?php 

include './DbHandler.php';

$db = new DbHandler();

$json = $_POST["usersJSON"];
//echo $json; 
if (get_magic_quotes_gpc()){
$json = stripslashes($json);
}
$data = json_decode($json);
$admin=$data[0]->field1;
$name=$data[0]->field2;
$mobile=$data[0]->field3;
$email=$data[0]->field4;
$pincode=$data[0]->field6;
$voter_id=$data[0]->field7;
$account_id=$data[0]->field8;
//echo "$mobile"; 

$response = array();

if ($mobile && $mobile!='') {

    //$admin=$_POST['admin']; 

    //$name = $_POST['name'];

    //$email = $_POST['email'];

    //$mobile = $_POST['mobile'];

 

    $otp = rand(100000, 999999);

 //echo "name:$name";

//echo "email:$email";

//echo "mobile:$mobile";

//echo "otp:$otp";

   $res = $db->createUser($admin,$name, $email, $mobile,$pincode,$voter_id,$account_id, $otp);

 //echo "printres\n";

//echo "$res";

    if ($res == USER_CREATED_SUCCESSFULLY) {

         //echo "\nhello\n";

        // send sms

        sendSms($mobile,$account_id, $otp);

//echo "hi";
        $id=$db->getUserId($mobile,$account_id); 

        $response["error"] = false;
 $pincode=$db->getPincode($mobile,$account_id);

        $response["message"] = "SMS request is initiated! You will be receiving it shortly.";
	$response["id"]=$id;
 $response["pincode"]=$pincode;


    } else if ($res == USER_CREATE_FAILED) {

        $response["error"] = true;

        $response["message"] = "Sorry! Error occurred in registration.";

    } else if ($res == USER_ALREADY_EXISTED) {

        $response["error"] = true;

        $response["message"] = "Mobile number already existed!";

    }

} else {

    $response["error"] = true;

    $response["message"] = "Sorry! mobile number is not valid or missing.";

}

echo json_encode($response);

 

function sendSms($mobile,$account_id, $otp) {

     

    $otp_prefix = ':';

//echo "hi";

    //Your message to send, Add URL encoding here.
	if($account_id==1){

    $message = urlencode("OTP for logging on to Polltics-Voter Relationship Management App LOCDIS is $otp. Polltics-Turn Undecided Voters In Your Favor");
        }
	else
	{
	$message = urlencode("OTP for logging on to grievance app is $otp.");
	}

// echo "$message";
//echo "$mobile";

    $response_type = 'json';

 

    //Define route 

    $route = "4";

     

    //Prepare you post parameters

    $postData = array(

        'phone_number' => $mobile,

        'content' => $message,

    );

//API URL

    $url = "http://vigeon.voonik.com/sms_notifications/send_otp_sms";

 $header=array('authorization: Basic MTZoaGFpMjU5ZjcxMGRsNmpoZTE5ZmI6eA==','cache-control: no-cache' ,'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW','postman-token: 735180f8-e056-1f9e-6a64-acaa26422c8f');



// init the resource

    $ch = curl_init();

    curl_setopt_array($ch, array(

        CURLOPT_URL => $url,

        CURLOPT_RETURNTRANSFER => true,

        CURLOPT_POST => true,

        CURLOPT_POSTFIELDS => $postData,

        CURLOPT_HTTPHEADER => $header


    ));

 
$content  = curl_exec($ch);
//echo $content;
//echo "$ch"; 

    //curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
    if (curl_errno($ch)) {

        echo 'error:' . curl_error($ch);

    }

 

    curl_close($ch);

}

?>
