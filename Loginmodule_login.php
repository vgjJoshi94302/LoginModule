<?php
require "Loginmodule.php";

$user_name = $_POST["identifier_username"];
$user_pass = $_POST["identifier_password"];

//$user_name = "shrey";
//$user_pass = "shreypass";

$query="SELECT * FROM login WHERE username like '$user_name' and password like '$user_pass'";
$result =mysqli_query($con,$query);
if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	$name=$row["username"];
	$pass=$row["password"];
	echo "true"."Data was successfully inserted"."$name"."$pass";
}
else

{
	echo "Login was not successful"."Data was failed to insert";
}

?>