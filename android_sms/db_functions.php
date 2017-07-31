<?php

class DB_Functions {

    private $db;

    //put your code here
    // constructor
    function __construct() {
        include_once './db_connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }

    // destructor
    function __destruct() {

    }

    /**
     * Storing new user
     * returns user details
     */
	public function storeUser($Id,$f6,$f7,$f8,$f9,$f10,$f11,$f12,$f13,$f14,$f15,$f16,$f17,$f18,$f19,$f20,$f21,$f22,$f23,$f24,$f25,$f26,$f27,$f28,$f29,$f30,$f31,$f32,$f33,$f34,$f35,$f36,$f37,$f38,$f39,$f40,$f41,$f42,$f43,$f44,$f45,$f46,$f47,$f48,$f49,$f50) { 
      // Insert user into database
$result = mysql_query("INSERT INTO survey_data VALUES('','$f6','$f7','$f8','$f9','$f10','$f11','$f12','$f13','$f14','$f15','$f16','$f17','$f18','$f19','$f20','$f21','$f22','$f23','$f24','$f25','$f26','$f27','$f28','$f29','$f30','$f31','$f32','$f33','$f34','$f35','$f36','$f37','$f38','$f39','$f40','$f41','$f42','$f43','$f44','$f45','$f46','$f47','$f48','$f49','$f50')");
        if ($result) {
			return true;
        } else {
			if( mysql_errno() == 1062) {
				// Duplicate key - Primary Key Violation
				return true;
			} else {
				// For other errors
				return false;
			}            
        }
    
}

public function storeFeedback($f1,$f2,$f3,$f4){

$sql ="SELECT ticker_id FROM feedback_customers ORDER BY ticker_id ASC";
 $res = mysql_query($sql);
 
 $id = 0;
 //echo mysql_fetch_array($res);
 while($row = mysql_fetch_array($res)){
//echo "hi";
 $id = $row['ticker_id'];
//echo $id;
 } 
$id=$id+1;
 $path = "Img_Upload/$id.png";
 
 $actualpath = "http://poolana9027.cloudapp.net/android_sms/$path";
 
 
 mysqli_close($con);
     $result=mysql_query("INSERT INTO feedback_customers(customer_id,pincode,feedback_image,status,created_at) VALUES('$f1','$f2','$actualpath','$f4',now())");
 if ($result) {
//echo base64_decode($f3);
 file_put_contents($path,base64_decode($f3));
                        return true;
        } else {
                        if( mysql_errno() == 1062) {
                                // Duplicate key - Primary Key Violation
                                return true;
                        } else {
                                // For other errors
                                return false;
                        }
        }

}


	 /**
     * Getting all users
     */
    public function getAllUsers() {
        $result = mysql_query("select * FROM users");
        return $result;
    }
}

?>
