<?php
include 'database.php';
include 'uploadImageAndLink.php';

$gestureJSON=null;
$activityJSON=null;

$dummy_hardcoded = "0xDEADBEEF";

$data = file_get_contents('php://input');
parse_str($data);
if($gestureJSON){
	$json_a=json_decode($gestureJSON, true);
	$query="INSERT INTO platform (platform, did) VALUES ('$json_a["platform"]', '$json_a["androidDeviceID"]')";
	$result=$mysqli->query($query);
	if($result==-1){
		echo FAILURE;
	}
	else{
		echo SUCCESS;
	}
	$query2="INSERT INTO clients (cid, platform) VALUES ('$dummy_hardcoded', '$json_a["platform"]')";
	$result=$mysqli->query($query2);
	if($result==-1){
		echo FAILURE;
	}
	else{
		echo SUCCESS;
	}
	$query3="INSERT INTO gestures (activityname, timestamp) VALUES ('$json_a["activityName"]', '$json_a["timeStamp"]')";
	$result=$mysqli->query($query3);
	if($result==-1){
		echo FAILURE;
	}
	else{
		echo SUCCESS;
	}
	for($i=0; $i<count($json_a['gestureDataArray']);$i++){
		$query4="INSERT INTO coordinates (x, y, state) VALUES ('$json_a["gestureDataArray"][$i]["x_cord"]', '$json_a["gestureDataArray"][$i]["y_cord"]', '$json_a["gestureDataArray"][$i]["state"]')";
		$result=$mysqli->query($query4);
		if($result==-1){
			echo FAILURE;
		}
		else{
			echo SUCCESS;
		}
	}
}
else if($activityJSON){
	$json_a=json_decode($activityJSON, true);
	$link = uploadImage($json_a['activityScreenshot'], $json_a['activityName']);
	$query="INSERT INTO activities (activityname, url, timestamp) VALUES ('$json_a["activityName"]', '$link')";
	$result=$mysqli->query($query);
	if($result==-1){
		echo FAILURE;
	}
	else{
		echo SUCCESS;
	}
}
else{
	echo FAILURE;
}




# PARSE JSON INTO ITS STUFFS

	
?>