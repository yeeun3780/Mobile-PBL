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



업로드는 여러가지 방법이 있다. ByteArrayOutputStream, URI경로, 직접 파일을 올리는 방법 등