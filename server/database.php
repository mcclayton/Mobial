<?php
	$DB_HOST = "mysql17.000webhost.com";
	$DB_TABLE = "a1218865_analys";
	$DB_USER = "a1218865_mobial";
	$DB_PASS = "espr3ssbros";

  // Create connection
  $mysqli=mysqli_connect($DB_HOST,$DB_USER,$DB_PASS,$DB_TABLE);

  // Check connection
  if (mysqli_connect_errno($mysqli)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    exit;
  }
  //define some constants
  define("FAILURE", "-1\n");
  define ("SUCCESS", "1\n");
?>