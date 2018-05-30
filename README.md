# QuizAggregate
Spring Boot + Cloudant
https://www.ibm.com/blogs/bluemix/2017/05/getting-started-with-microservices-using-spring-boot-and-cloudant/

DB接続先
https://fea01fa8-c228-41f0-9e59-e7763dfb2820-bluemix.cloudant.com/user_answear/8902567215c0ff7bda1197d964647773

##memo
Spring Boot framework will automatically serialize the entity object with the response from the Cloudant database as well as convert it to JSON when sending the request back for the REST request.

https://www.niandc.co.jp/sol/tech/date20180209_1610.php

https://github.com/icha024/cloudant-spring-boot-starter

# Thymeleaf
## 変数式：${hoge}
## ユーティリティオブジェクト
直接クラスメソッドが呼ばるようになる<br>
```
${#dates.format(new java.util.Data(), 'dd/MM/yyyy')}<br>
```
Dateクラスのformatメソッドで、日付整形

# パラメータへのアクセス
pram変数を利用

## メッセージ式 #{値の指定}
プロパティファイルから値を読み出す

## サービス層
サービス層：コントローラとモデルの両者から自由に呼び出せるもの
Spring Frameworkでは、このサービス部分をBeanとして登録し、アノテーションを記述して利用する仕組み

## Autowiredについて
Spring Bootで@Controller @Service @Repository@Componentといったアノテーションを付与したクラスはBeanとしてSpringのDIコンテナに登録され、利用するクラス側で@Autowiredアノテーションを当該クラスに付与することで、Springが生成したオブジェクトを利用できます。
