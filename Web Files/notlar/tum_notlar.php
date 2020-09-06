<?php
    $response = array();
    
    require_once __DIR__ . '/db_config.php';
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "SELECT * FROM notlar";
    $result = mysqli_query($baglanti, $sqlsorgu);
    
    if (mysqli_num_rows($result) > 0) {
        
        $response["notlar"] = array();
        
        while ($row = mysqli_fetch_assoc($result)) {
            // temp user array
            $notlar = array();
            
            $notlar["not_id"] = $row["not_id"];
            $notlar["ders_adi"] = $row["ders_adi"];
            $notlar["not1"] = $row["not1"];
            $notlar["not2"] = $row["not2"];
            
            array_push($response["notlar"], $notlar);
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
