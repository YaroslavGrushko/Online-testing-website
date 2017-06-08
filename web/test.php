
<!--/**
 * Created by IntelliJ IDEA.
 * User: Ярослав
 * Date: 08.07.2016
 * Time: 11:06
 */-->
<html>
<head>
    <title>Test</title>

</head>

<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<body bgcolor=" 46 82 B4 " text="black">
<br><br><br>
<?php
error_reporting(E_ALL);
//error_reporting(0); //отключить вывод ошибок и предупреждений

session_start();  // открываем сессию (возможность создать глобальную переменную)
$_SESSION["client"] =1;
if (!isset($_SESSION['count'])) {
    $_SESSION['count'] = 0;
}
//isset($_SESSION['count1']);
$_SESSION['count1'] = 1;
if ($_SESSION['count1'] == 1)
{

echo 'Привет, мир!';
};
?>
<div id="container">
Question

<fieldset>
    <p>
        <label>Гарантийное обслуживание:</label>
        Да <input type=radio name="Warranty" value="Да">
        Нет <input type=radio name="Warranty" value="Нет">
    </p>
Ansvers
</fieldset>

</div>
</body>
</html>