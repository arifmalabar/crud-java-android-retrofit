<?php
require 'koneksi_db_api.php';
$data = [];
$query = mysqli_query($koneksi, "SELECT * FROM tb_pegawai");

$cek = mysqli_affected_rows($koneksi);
if($cek > 0){
    $data["status"] = 1;
    $data["pesan"] = "berhasil";
    $data["data"] = array();    
    while ($a = mysqli_fetch_object($query)) {
        $data["f"]["id"] = $a->id;
        $data["f"]["nama"] = $a->nama;
        $data["f"]["alamat"] = $a->alamat;
        $data["f"]["golongan"] = $a->golongan;
        array_push($data["data"], $data["f"]);
    }   
} else {
    $data["status"] = 0;
    $data["pesan"] = "Data Tidak Tersedia";
}
echo json_encode($data);