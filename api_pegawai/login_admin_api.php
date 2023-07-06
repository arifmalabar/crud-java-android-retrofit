<?php
include 'koneksi_db_api.php';
$username = $_POST['username'];
$password = md5($_POST['password']);
$sql = "SELECT * FROM tb_user WHERE username = '".$username."' AND password='".$password."' ";
$query = mysqli_query($koneksi, $sql);
$data = [];
if(mysqli_num_rows($query) > 0)
{
    $data["status"] = 1;
    $data["pesan"] = "berhasil";
    $data["data"] = array();
    while($row = mysqli_fetch_object($query))
    {
        $data["f"]["id_user"] = $row->id_user;
        $data["f"]["nama"] = $row->nama;
        $data["f"]["username"] = $row->username;
        $data["f"]["password"] = $row->password;
        array_push($data["data"], $data["f"]);
    }
} else {
    $data["status"] = 0;
    $data["pesan"] = "gagal login, username password anda salah";
    $data["data"] = array();
}
echo json_encode($data);