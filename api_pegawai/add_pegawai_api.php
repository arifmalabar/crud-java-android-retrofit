<?php 
require 'koneksi_db_api.php';
$nama = $_POST['nama'];
$alamat = $_POST['alamat'];
$golongan = $_POST['golongan'];

$query = mysqli_query($koneksi, "INSERT INTO `tb_pegawai`(`nama`, `alamat`, `golongan`) VALUES ('$nama','$alamat','$golongan')");
if($query){
    echo json_encode(["status" => "berhasil"]);
} else{
    echo json_encode(["status" => "gagal"]);
}