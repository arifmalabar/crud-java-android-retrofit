<?php
require 'koneksi_db_api.php';
$id = $_GET['id'];
$query = mysqli_query($koneksi, "DELETE FROM `tb_pegawai` WHERE id='$id'");
if($query){
    echo json_encode(["status" => "berhasil"]);
} else{
    echo json_encode(["status" => "gagal"]);
}