<?php
	 `touch a.txt`;
$f= file_put_contents("/var/www/html/android_sms/Img_Upload/test.php","hi");
if($f){
echo "1";
}
else{
echo "3";
}
?>
