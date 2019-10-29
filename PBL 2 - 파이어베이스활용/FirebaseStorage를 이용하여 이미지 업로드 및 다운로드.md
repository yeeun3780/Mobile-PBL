Firebase Storage를 이용하여 실시간으로 이미지 업로드 및 다운로드 하기

Firebase Storage 문서, 사진, 파일, 동영상을 저장할 수 있는 저장소

```
//가장 먼저, FirebaseStorage 인스턴스를 생성한다                          //getInstance() 파라미터에 들어가는 값은 firebase console에서 //storage를 추가하면 상단에 gs:// 로 시작하는 스킴을 확인할 수 있다FirebaseStorage storage = FirebaseStorage.getInstance("gs://my-custom-bucket");//위에서 생성한 FirebaseStorage 를 참조하는 storage를 생성한다StorageReference storageRef = storage.getReference();// 위의 저장소를 참조하는 images폴더안의 space.jpg 파일명으로 지정하여// 하위 위치를 가리키는 참조를 만든다StorageReference spaceRef = storageRef.child("images/space.jpg");

Uri file = Uri.fromFile(new File(pathName);
uploadTask = riversRef.putFile(file);

// 파일 업로드의 성공/실패에 대한 콜백 받아 핸들링 하기 위해 아래와 같이 작성한다uploadTask.addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception exception) {
        // Handle unsuccessful uploads
    }
}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
    }
});

// 인스턴스 생성
StorageReference storageRef = storage.getReference();
StorageReference spaceRef = storageRef.child("images/space.jpg");
// 액티비티 내에 보여주고 싶은 이미지뷰 바인딩
ImageView imageView = (ImageView) findViewById(R.id.imageView);

// Glide Library 사용하여 화면에 바로 보여줄 수 있다
Glide.with(this /* context */)
        .using(new FirebaseImageLoader())
        .load(spaceRef)
        .into(imageView);
```

FirebaseStorage 



보안 규칙의 종류

인증된 사용자

 allow read, wirte: if request.auth != null; 

인증되지 않은 사용

 allow read, wirte: if true ; 

쓰기 금지

 allow read, wirte: if false; 



저장된 이미지 보기

* 저장된 이미지를 다운로드해서 뷰페이지로 연결 : 로컬로 다운로드 받을 때는 getFile()메소드 사용 메모리로 다운 받을 때는 getBytes(), getStream() 메소드 사용
* 안드로이드 스튜디오에서 스토리지 레퍼런스를 가져오게 되면 파일을 업로드할 경로와 파일명을 생성해서 접근해야 된다.(고유의 파일명 생성 덮어쓰기 방지를 위해서)
* 권장하는 이미지 다운 받법은  Glide 라이브러리를 포함하는 파이어베이스 UI를 사용하는 것(이미지 사이즈 조정 및 이미지 파일이 커서 앱이 다운되는 것을 방지)

* 메인 액티비티 자바에서 데이터베이스에 이미지의 경로와 파일을 저장한 데이터를 불러와서 뷰페이저 어댑터에 데이터를 전달하고 그 데이터에 해당하는 스토리지에 접근해서 불러오면 화면이  완성이 된다.



결론

* 파이어베이스의 데이터베이스는 이미지 저장은 되지 않기 때문에 이미지를 불러오기 위해서는 Storage 서비스를 이용해야 한다. 