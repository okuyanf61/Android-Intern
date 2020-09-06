<?php
    $response = array();
    
    require_once __DIR__ . '/db_config.php';
    
    $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
    
    if (!$baglanti) {
        die("Hatalı bağlantı : " . mysqli_connect_error());
    }
    
    $sqlsorgu = "SELECT * FROM filmler,kategoriler,yonetmenler WHERE filmler.kategori_id = kategoriler.kategori_id and filmler.yonetmen_id = yonetmenler.yonetmen_id";
    $result = mysqli_query($baglanti, $sqlsorgu);
    
    if (mysqli_num_rows($result) > 0) {
        
        $response["filmler"] = array();
        
        while ($row = mysqli_fetch_assoc($result)) {
            $kategoriler = array();
            $kategoriler["kategori_id"] = $row["kategori_id"];
            $kategoriler["kategori_ad"] = $row["kategori_ad"];
            
            $yonetmenler = array();
            $yonetmenler["yonetmen_id"] = $row["yonetmen_id"];
            $yonetmenler["yonetmen_ad"] = $row["yonetmen_ad"];
            
            $filmler = array();
            $filmler["film_id"] = $row["film_id"];
            $filmler["film_ad"] = $row["film_ad"];
            $filmler["film_yil"] = $row["film_yil"];
            $filmler["film_resim"] = $row["film_resim"];
            $filmler["kategori"] = $kategoriler;
            $filmler["yonetmen"] = $yonetmenler;
            
            
            
            array_push($response["filmler"], $filmler);
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
