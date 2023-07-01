<?php
require 'koneksi_db_api.php';
$id = $_GET['id'];
$nama = $_POST['nama'];
$alamat = $_POST['alamat'];
$golongan = $_POST['golongan'];
$query = mysqli_query($koneksi, "UPDATE `tb_pegawai` SET `nama`='$nama',`alamat`='$alamat',`golongan`='$golongan' WHERE id = '$id'");
if($query){
    echo json_encode(["status" => "berhasil"]);
} else{
    echo json_encode(["status" => "gagal"]);
}