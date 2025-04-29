#  Program Investasi Sederhana Berbasis Command Line
Program Investasi Sederhana Berbasis Command Line ini merupakan aplikasi Java berbasis terminal yang dibuat untuk mensimulasikan aktivitas investasi saham dan Surat Berharga Negara (SBN), sebagai bagian dari tugas Pemrograman Berorientasi Objek (PBO). Program ini memiliki dua jenis pengguna, yaitu Admin dan Customer. Admin memiliki akses untuk login, menambahkan produk investasi seperti saham dan SBN, serta mengubah harga saham. Sementara itu, Customer dapat melakukan login, membeli dan menjual saham, membeli SBN, melakukan simulasi investasi SBN, dan melihat portofolio investasinya. Seluruh proses login dilakukan melalui akun yang telah di-hardcode, dan semua data disimpan dalam memori (tanpa file atau database eksternal). Struktur program dibangun menggunakan beberapa kelas, yaitu: User (sebagai abstract class yang diturunkan menjadi Admin dan Customer), Saham, SuratBerhargaNegara, Portofolio, dan Input untuk validasi data. Aplikasi ini memiliki navigasi menu berbasis teks yang memisahkan fitur untuk admin dan customer. 

# Identitas Kami
- Nama  : I Kadek Bayu Mahardika Suputra(2405551117)
- Nama  : I Ketut Bagus Nugraha (2405551139)
  
# UML
![UML drawio](https://github.com/user-attachments/assets/b4e652b4-9144-48b7-990c-6fdb601a6e06)
Diagram UML ini menggambarkan arsitektur sistem aplikasi investasi yang terdiri dari beberapa class utama yang saling terhubung. Class InvestasiApp menjadi titik awal aplikasi dan mengarahkan pengguna berdasarkan hasil login yang ditangani oleh class Login, yang memverifikasi kredensial dan membedakan antara peran Admin dan Customer, keduanya merupakan subclass dari User. Setelah login berhasil, pengguna diarahkan ke MainMenu yang selanjutnya menavigasi ke AdminMenu atau CustomerMenu sesuai peran. AdminMenu memberikan akses bagi admin untuk mengelola data saham dan SBN melalui AdminController, sementara CustomerMenu memungkinkan customer melakukan transaksi seperti beli/jual saham dan SBN serta melihat portofolio melalui CustomerController. Data investasi direpresentasikan oleh class Saham dan SBN, sedangkan Portofolio menyimpan catatan kepemilikan aset customer. Untuk memastikan validitas pembelian, class SBNValidation berperan dalam memverifikasi transaksi terkait SBN agar sesuai batasan dan aturan sistem.

# Cara Kerja  Program
1. Inisialisasi Aplikasi
Biasanya dimulai dari kelas seperti MainMenu.java atau InvestmentApp.java. Meskipun Main.java hanya mencetak "Hello, World!", kelas utama lain kemungkinan dipanggil secara tidak langsung atau dari GUI utama.

2. Tampilan Menu Awal
* Kelas MainMenu.java bertanggung jawab untuk menampilkan menu utama.
* Pengguna bisa memilih untuk login sebagai:
-- Admin
-- Customer

3. Login
* Setelah memilih peran, aplikasi akan meminta input username dan password.
* Autentikasi dilakukan melalui LoginUtil.java, yang memverifikasi data pengguna (mungkin terhadap daftar user statis atau file data).

4. Pengalihan ke Controller
* Setelah login sukses:
* Jika Admin, maka AdminController.java diaktifkan.
* Jika Customer, maka CustomerController.java dipanggil.

5. Fungsi Admin (AdminController)
* Menambahkan atau menghapus investasi (Saham/SBN).
* Mengelola data pengguna dan portfolio.
* Melihat laporan keseluruhan pengguna.

6. Fungsi Customer (CustomerController)
* Melihat daftar investasi yang tersedia.
* Membeli investasi (menambahkan ke portfolio).
* Melihat portfolio pribadi.

7. Model-Model Data (model/)
* User, Admin, Customer: menyimpan info login dan identitas pengguna.
* Saham, SBN: representasi jenis investasi.
* PortfolioItem, SBNPortfolioItem: mencatat investasi yang dimiliki user.
* InvestmentApp.java: Untuk menyimpan daftar user, daftar produk investasi, dan logika utama.

# Penggunaan Program 
Di bawah ini adalah contoh dari penggunaan program investasi sederhana berbasis command line beserta hasil screenshoot. Terdapat juga beberapa penjelasan tentang bagaiamana kode ini berjalan.

# Menu Login
![image](https://github.com/user-attachments/assets/7a2cc7ec-8719-4c34-b3bf-b5764f3ceafd)
![image](https://github.com/user-attachments/assets/186a078e-6717-4217-ac65-a344547a9eca)
Gambar tersebut menampilkan antarmuka menu login berbasis teks untuk dua jenis pengguna, yaitu admin dan customer. Kedua menu menunjukkan tampilan yang serupa, dimulai dengan sambutan “SELAMAT DATANG” dan dua opsi utama: 1 untuk Login dan 0 untuk Keluar. Setelah memilih opsi login, pengguna diminta memasukkan username dan password. Pada contoh, pengguna pertama menggunakan kredensial admin dan admin01, sedangkan pengguna kedua menggunakan customer dan cust01. Tampilan ini menunjukkan sistem autentikasi sederhana berbasis peran yang dapat digunakan untuk membedakan hak akses antara admin dan pelanggan.

# Menu Administrator
![image](https://github.com/user-attachments/assets/eec7f49f-cdc1-48f5-accf-03450a4bc90c)

Menu Administrator adalah tampilan utama bagi pengguna dengan hak akses admin, yang berfungsi sebagai pusat kontrol pengelolaan data investasi. Di menu ini, admin dapat memilih beberapa opsi seperti menambahkan saham baru ke sistem, memperbarui harga saham yang sudah ada, menambahkan produk Surat Berharga Negara (SBN), atau kembali ke menu sebelumnya. Antarmuka ini memastikan bahwa hanya admin yang dapat memodifikasi atau menambahkan produk investasi yang nantinya bisa diakses oleh customer.
## Tambah Saham
![image](https://github.com/user-attachments/assets/1f8a28d5-8f3b-408e-b49c-1622c2d1a2c3)

Pada bagian ini, admin memilih opsi "Tambah Saham" untuk memasukkan data saham baru ke dalam sistem. Admin diminta untuk mengisi kode saham, nama perusahaan, dan harga saham. Setelah data dimasukkan, sistem memberikan konfirmasi bahwa saham berhasil ditambahkan. Fitur ini memungkinkan admin memperluas daftar produk saham yang dapat dibeli oleh customer dalam menu transaksi mereka.
## Ubah Harga saham
![image](https://github.com/user-attachments/assets/0770858f-d862-4bfa-869a-f9c63f6b73b9)

Menu ini digunakan oleh admin untuk memperbarui harga saham yang telah terdaftar sebelumnya. Admin memasukkan kode saham yang ingin diubah dan kemudian memasukkan harga baru. Setelah itu, sistem akan mengonfirmasi bahwa perubahan harga telah berhasil dilakukan. Fitur ini penting untuk menjaga agar harga saham yang tersedia selalu sesuai dengan nilai pasar terkini atau kebijakan internal.
## Tambah SBN
![image](https://github.com/user-attachments/assets/9e27eb13-a383-4bdd-9ff6-ed251aee9983)

Melalui menu ini, admin dapat menambahkan produk investasi berupa Surat Berharga Negara (SBN) dengan mengisi nama SBN, tingkat bunga, jangka waktu, tanggal jatuh tempo, dan kuota nasional. Setelah semua data terisi, sistem menampilkan pesan keberhasilan penambahan SBN. Fungsi ini bertujuan menyediakan pilihan investasi lain selain saham kepada customer, terutama yang berbasis kupon dan jatuh tempo.

# Menu Customer
![image](https://github.com/user-attachments/assets/afec53d8-f1e6-4a88-8ea3-2dc4c7dc04fa)

Menu Customer adalah tampilan utama bagi pengguna dengan peran customer, yaitu investor atau pembeli produk investasi. Di sini terdapat beberapa pilihan seperti beli saham, jual saham, beli SBN, simulasi SBN, lihat portofolio, dan kembali. Menu ini memungkinkan customer melakukan berbagai aktivitas transaksi dan pemantauan aset secara langsung melalui antarmuka berbasis teks yang sederhana namun fungsional.
## Beli Saham
![image](https://github.com/user-attachments/assets/c87260f3-f92a-4825-802b-52bdd392e33c)

Fitur beli saham memungkinkan customer memilih dan membeli saham yang tersedia di sistem. Daftar saham ditampilkan lengkap dengan kode, nama perusahaan, dan harga per lembar. Setelah customer memilih saham dan jumlah pembelian, sistem mencatat transaksi dan memberikan konfirmasi pembelian. Ini merupakan salah satu fitur inti dari sistem investasi yang memberi akses langsung terhadap kepemilikan saham.
## Jual Saham
![image](https://github.com/user-attachments/assets/970e6d97-8dfb-4ded-a19d-f142fc620806)

Melalui menu ini, customer dapat menjual kembali saham yang telah mereka beli sebelumnya. Sistem menampilkan daftar saham yang dimiliki customer, kemudian pengguna memilih saham mana yang ingin dijual dan jumlah lembar yang akan dilepas. Setelah data dimasukkan, sistem mengonfirmasi bahwa transaksi penjualan berhasil. Fitur ini penting agar customer bisa merealisasikan keuntungan atau mengelola portofolionya.
## Beli SBN
![image](https://github.com/user-attachments/assets/c930371d-d26a-4520-a435-46ba5a341fd3)

Fitur ini digunakan customer untuk membeli produk Surat Berharga Negara. Sistem menampilkan daftar SBN lengkap dengan informasi kuota dan bunga, lalu customer memilih nama SBN dan nominal pembelian. Setelah konfirmasi, sistem mencatat transaksi pembelian dan memberi umpan balik sukses. Dengan fitur ini, customer dapat mendiversifikasi investasinya di luar saham dengan instrumen yang lebih stabil seperti SBN.
## Simulasi SBN
![image](https://github.com/user-attachments/assets/7b9f88e9-8489-4257-82a1-d3c386c50fde)

Menu simulasi SBN memungkinkan customer melihat estimasi keuntungan bulanan dari SBN yang mereka miliki. Setelah memilih SBN dan memasukkan nominal, sistem menghitung dan menampilkan simulasi kupon atau bunga bulanan yang akan diterima. Fitur ini bersifat informatif dan membantu customer mengambil keputusan sebelum atau setelah melakukan pembelian SBN.
## Lihat Portopolio
![image](https://github.com/user-attachments/assets/1af03ad3-e66e-4174-85be-a48ab5fb0f28)

Fitur ini menyajikan daftar kepemilikan aset investasi customer, baik saham maupun SBN, secara rinci. Untuk saham, ditampilkan kode, nama perusahaan, jumlah lembar, dan nilai total investasi. Untuk SBN, informasi mencakup nama, nominal, kupon per bulan, bunga, jangka waktu, dan tanggal jatuh tempo. Portofolio ini membantu customer memantau perkembangan investasinya dan mengambil keputusan strategis.


