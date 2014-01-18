<?php
function uploadImage($data, $filename){
	define('UPLOAD_DIR', 'screens/');
	$image=base64_decode($data);
	$filepath= UPLOAD_DIR . $filename . '.bmp';
	$success= file_put_contents($image, $filepath);
	if($success==false){
		echo FAILURE;
	}
	else{
		return $filepath;
	}
}
?>