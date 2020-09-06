<?php
    $response = array();
    
    require_once __DIR__ . '/db_config.php';
    
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "SELECT * FROM kategoriler";
    $result = mysqli_query($baglanti, $sqlsorgu);
    
    if (mysqli_num_rows($result) > 0) {
        
        $response["kategoriler"] = array();
        
        while ($row = mysqli_fetch_assoc($result)) {
            $kategoriler = array();
            $kategoriler["kategori_id"] = $row["kategori_id"];
            $kategoriler["kategori_ad"] = $row["kategori_ad"];
            
            array_push($response["kategoriler"], $kategoriler);
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
