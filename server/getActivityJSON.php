<?php
include 'database.php';

$client_id = $_GET["cid"];
$query = "SELECT * FROM clients LEFT OUTER JOIN platform ON platform.platform=clients.platform LEFT OUTER JOIN devices ON devices.did=platform.did LEFT OUTER JOIN activities ON activities.activityname=devices.activityname WHERE clients.cid LIKE '$client_id'";
$result=$mysqli->query($query);
if($result){
	while($row = $result->fetch_assoc()){
		$json = array(
			'platform' = $row['platform'],
			'androidDeviceID' = $row['did'],
			'activityScreenshot' = $row['url'],
			'activityName' = $row['activityname'],
			'timeStamp' = $row['time'],
		);
		$JSON = json_encode($json);
		echo $JSON;
		echo "\r\n";
	}
}
else{
	echo FAILURE;
}
?>