<?php
include 'database.php';

$data = json_decode(file_get_contents('php://input'));
echo "<pre>";
print_r($data);
echo "</pre>";
return 0;
	
?>