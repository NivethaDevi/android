<?php

 

/**

 * Class to handle all db operations

 * This class will have CRUD methods for database tables

 *

 * @author Ravi Tamada

 * @link URL Tutorial link

 */

class DbHandler {

 

    private $conn;

 

    function __construct() {

        require_once dirname(__FILE__) . '/DbConnect.php';

        // opening db connection

        $db = new DbConnect();

        $this->conn = $db->connect();

    }

 

    /* ------------- `users` table method ------------------ */

 

    /**

     * Creating new user

     * @param String $name User full name

     * @param String $email User login email id

     * @param String $mobile User mobile number

     * @param String $otp user verificaiton code

     */

    public function createUser($admin,$name, $email,$mobile, $pincode,$voter_id,$account_id, $otp) {

        $response = array();

//echo "hi"; 

        // First check if user already existed in db

        if (!$this->isUserExists($mobile,$account_id)&&!$this->isExistingUserExists($mobile,$account_id)){

 

            // Generating API key

            $api_key = $this->generateApiKey();

 //echo "apikey\n";


//echo "$api_key";

            // insert query

            $stmt = $this->conn->prepare("INSERT INTO users(admin,name, email, mobile,pincode,voter_id,account_id, apikey, status) values(?,?, ?,?, ?,?,?, ?, 0)");

//echo "stmt\n";

//echo "$stmt";

            $stmt->bind_param("ssssisis",$admin, $name, $email, $mobile,$pincode, $voter_id,$account_id,$api_key);

 //echo "lusu\n"

            $result = $stmt->execute();

 //echo "bad\n";

            $new_user_id = $stmt->insert_id;

 //echo "gud\n";

            $stmt->close();

 

            // Check for successful insertion

            if ($result) {

 

                $otp_result = $this->createOtp($new_user_id,$account_id, $otp);

//echo "hi";

 //echo "$otp_result";

                // User successfully inserted

                return USER_CREATED_SUCCESSFULLY;

            } else {

//echo "no";

                // Failed to create user

                return USER_CREATE_FAILED;

            }

        } else {

            // User with same email already existed in the db

    if($this->isNewUserExists($mobile,$account_id)){

$val=$this->isNewUserExists($mobile,$account_id);

//echo "$val";

                $otp_result = $this->createOtp($val,$account_id, $otp);

//echo "hi";

 //echo "$otp_result";

                // User successfully inserted

                return USER_CREATED_SUCCESSFULLY;

            }

            else{

            return USER_ALREADY_EXISTED;

            }

        }

//echo "\nhello\n";

//iecho $response; 

        return $response;

    }

 

    public function createOtp($user_id,$account_id, $otp) {

 //echo "hi";
//echo $otp;
        // delete the old otp if exists

        $stmt = $this->conn->prepare("DELETE FROM sms_codes where user_id = ? AND account_id=?");

//echo "hi";

        $stmt->bind_param("ii", $user_id,$account_id);

//echo "$stmt";

        $stmt->execute();

 

 

        $stmt = $this->conn->prepare("INSERT INTO sms_codes(user_id,account_id, code, status) values(?, ?,?, 0)");

        $stmt->bind_param("iis", $user_id, $account_id,$otp);

 

        $result = $stmt->execute();

 

        $stmt->close();

// echo $result;

        return $result;

    }

     

    /**

     * Checking for duplicate user by mobile number

     * @param String $email email to check in db

     * @return boolean

     */

    private function isUserExists($mobile,$account_id) {

  $stmt = $this->conn->prepare("SELECT id from users WHERE mobile = ? AND status=0 AND account_id=?");

        $stmt->bind_param("si", $mobile,$account_id);

        $stmt->execute();

        $stmt->store_result();

        $num_rows = $stmt->num_rows;
//echo "$mobile";
        $stmt->close();

        return $num_rows > 0;

    }

private function isExistingUserExists($mobile,$account_id) {

        $stmt = $this->conn->prepare("SELECT id from users WHERE mobile = ? and status=1 and account_id=?");

        $stmt->bind_param("si", $mobile,$account_id);

        $stmt->execute();

        $stmt->store_result();

        $num_rows = $stmt->num_rows;

        $stmt->close();
//	echo $mobile;
        return $num_rows > 0;

    }



private function isNewUserExists($mobile,$account_id) {

        $stmt = $this->conn->prepare("SELECT id from users WHERE mobile = ? and account_id=?");

        $stmt->bind_param("si", $mobile,$account_id);

        $stmt->execute();

        $stmt->store_result();

$stmt->bind_result($value);

$ress=$stmt->fetch();

        $num_rows = $stmt->num_rows;

        $stmt->close();

//echo "$value";

        return $value;

    }

 

    public function activateUser($otp) {

        $stmt = $this->conn->prepare("SELECT u.id, u.admin, u.name, u.email, u.mobile,u.pincode,u.voter_id,u.account_id, u.apikey, u.status, u.created_at FROM users u, sms_codes WHERE sms_codes.code = ? AND sms_codes.user_id = u.id AND sms_codes.account_id=u.account_id");

        $stmt->bind_param("s", $otp);

 

        if ($stmt->execute()) {

            // $user = $stmt->get_result()->fetch_assoc();

            $stmt->bind_result($id, $admin, $name, $email, $mobile,$pincode,$voter_id,$account_id, $apikey, $status, $created_at);

             

            $stmt->store_result();

 

            if ($stmt->num_rows > 0) {

                 

                $stmt->fetch();

                 

                // activate the user

                $this->activateUserStatus($id);

                 

                $user = array();
		$admin["admin"]=$admin;
                $user["name"] = $name;

                $user["email"] = $email;

                $user["mobile"] = $mobile;

		$user["pincode"]=$pincode;
		$user["voter_id"]=$voter_id;
		$user["account_id"]=$account_id;
                $user["apikey"] = $apikey;

                $user["status"] = $status;

                $user["created_at"] = $created_at;

                 

                $stmt->close();

                 

                return $user;

            } else {

                return NULL;

            }

        } else {

            return NULL;

        }

 

        return $result;

    }

public function getUserId($mobile,$account_id) {

        $stmt = $this->conn->prepare("SELECT id from users WHERE mobile = ? and account_id=?");

        $stmt->bind_param("si", $mobile,$account_id);

        $stmt->execute();

        $stmt->store_result();

$stmt->bind_result($value);

$ress=$stmt->fetch();

        $num_rows = $stmt->num_rows;

        $stmt->close();

//echo "$value";

        return $value;

    }
     

    public function activateUserStatus($user_id){

        $stmt = $this->conn->prepare("UPDATE users set status = 1 where id = ?");

        $stmt->bind_param("i", $user_id);

         

        $stmt->execute();

         

        $stmt = $this->conn->prepare("UPDATE sms_codes set status = 1 where user_id = ?");

        $stmt->bind_param("i", $user_id);

         

        $stmt->execute();

    }
public function updateFeedbackStatus($ticker_id){
//echo $ticker_id;
$stmt = $this->conn->prepare("UPDATE feedback_customers set status= 1 , closed_at= now() where ticker_id=?");
$stmt->bind_param("i",$ticker_id);
$status = $stmt->execute();
$res=array();
$b=array();
if($status){
	$res["error"]=false;
	$res["message"]="Updated Successfully";
	array_push($b,$res);
	echo json_encode($b);
}else{
	$res["error"]=true;
	$res["message"]="Updation Failed";
        array_push($b,$res);
	echo json_encode($b);
}
}

public function getAllFeedbacks_ep($pincode){
$stmt = $this->conn->prepare("SELECT ticker_id,feedback_image,status from feedback_customers WHERE pincode=?");

        $stmt->bind_param("i", $pincode);

        $stmt->execute();
$stmt->bind_result($value1,$value2,$value3);
//$stmt->fetch();
$res=array();
$res1=array();
while($stmt->fetch()){
$res["ticker_id"]=$value1;
$res["feedback_image"]=$value2;
$res["status"]=$value3;
array_push($res1,$res);
}
echo json_encode($res1);
        $stmt->close();

//return $value;


}
public function getAllFeedbacks($customer_id){

$stmt = $this->conn->prepare("SELECT ticker_id,feedback_image,status,created_at,closed_at from feedback_customers WHERE customer_id=?");

        $stmt->bind_param("i", $customer_id);

        $stmt->execute();
$stmt->bind_result($value1,$value2,$value3,$value4,$value5);
//$stmt->fetch();
$res=array();
$res1=array();
while($stmt->fetch()){
$res["ticker_id"]=$value1;
$res["feedback_image"]=$value2;
$res["status"]=$value3;
$res["created_at"]=$value4;
$res["closed_at"]=$value5;
array_push($res1,$res);
}
echo json_encode($res1);
        $stmt->close();

/*$res=array();
$res["ticker_id"]=$value;
$res["feedback_image"]=$value1;
$res["status"]=$value2;
echo json_encode($res);*/
//return $value;

}


 public function getPincode($mobile,$account_id){
$stmt = $this->conn->prepare("SELECT pincode from users WHERE mobile = ? And account_id=?");

        $stmt->bind_param("si", $mobile,$account_id);

        $stmt->execute();

        $stmt->store_result();

$stmt->bind_result($value);

$ress=$stmt->fetch();

        $num_rows = $stmt->num_rows;

        $stmt->close();

//echo "$value";

        return $value;
}


    /**

     * Generating random Unique MD5 String for user Api key

     */

    private function generateApiKey() {

        return md5(uniqid(rand(), true));

    }

}

?>
