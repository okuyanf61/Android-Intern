<?php
    
    $response = array();
    
    if (isset($_POST['not_id'])) {
        $not_id = $_POST['not_id'];
        
        require_once __DIR__ . '/db_config.php';
        
        $baglanti = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        
        if (!$baglanti) {
            die("Hatalı bağlantı : " . mysqli_connect_error());
        }
        
        $sqlsorgu = "DELETE FROM notlar WHERE notlar.not_id = $not_id";
        
        if (mysqli_query($baglanti, $sqlsorgu)) {
            
            $response["success"] = 1;
            $response["message"] = "successfully ";
            
            echo json_encode($response);
        } else {
            
            $response["success"] = 0;
            $response["message"] = "No product found";
            
            echo json_encode($response);
            
        }
        
        mysqli_close($baglanti);
    } else {
        
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";
        
        echo json_encode($response);
    }
    ?>


