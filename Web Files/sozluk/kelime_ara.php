<?php
    
    $response = array();
    
    if (isset($_POST['ingilizce'])) {
        $ingilizce = $_POST['ingilizce'];
        
        require_once __DIR__ . '/db_config.php';
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "SELECT * FROM kelimeler WHERE kelimeler.ingilizce like '%$ingilizce%'";
        $result = mysqli_query($baglanti, $sqlsorgu);
        
        if (mysqli_num_rows($result) > 0) {
            
            $response["kelimeler"] = array();
            
            while ($row = mysqli_fetch_assoc($result)) {
                $kelimeler = array();
                
                $kelimeler["kelime_id"] = $row["kelime_id"];
                $kelimeler["ingilizce"] = $row["ingilizce"];
                $kelimeler["turkce"] = $row["turkce"];
                
                array_push($response["kelimeler"], $kelimeler);
            }
            $response["success"] = 1;
            
            echo json_encode($response);
            
        } else {
            $response["success"] = 0;
            $response["message"] = "No data found";
            
            echo json_encode($response);
        }
        
        mysqli_close($baglanti);
        
        
    } else {
        
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";
        
        echo json_encode($response);
    }
    ?>




