<?php
$response = array();

require_once __DIR__ . '/db_config.php';

$baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
if (!$baglanti) {
    die("Hatalı bağlantı : " . mysqli_connect_error());
}
    
$sqlsorgu = "SELECT * FROM kisiler";
$result = mysqli_query($baglanti, $sqlsorgu);

if (mysqli_num_rows($result) > 0) {
    
    $response["kisiler"] = array();
    
    while ($row = mysqli_fetch_assoc($result)) {
        // temp user array
        $kisiler = array();
        
        $kisiler["kisi_id"] = $row["kisi_id"];
        $kisiler["kisi_ad"] = $row["kisi_ad"];
        $kisiler["kisi_tel"] = $row["kisi_tel"];
        
        array_push($response["kisiler"], $kisiler);
    }
    $response["success"] = 1;

    echo json_encode($response);
    
} else {
    $response["success"] = 0;
    $response["message"] = "No data found";

    echo json_encode($response);
}
mysqli_close($baglanti);
?>
