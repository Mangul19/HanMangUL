// icon = (success = 체크, info = i, error = X, warning = !, question = ?)
// denyTF = 취소 버튼 유무 (true = 취소버튼 생성, false = 취소버튼 숨김)
// msg = 메시지 내용
// confirmFunc = 확인시 함수
function SWalert(icon, denyTF , msg, confirmFunc, deniedFunc) {
    Swal.fire({ 
        icon: icon,
        title: msg,
        showDenyButton: denyTF,
        showCancelButton: false,
        confirmButtonText: '확인',
        denyButtonText: `취소`,
        allowOutsideClick: false
    }).then((result) => {
        if (result.isConfirmed) {
            if (isFunction(confirmFunc)) {
                window[confirmFunc]();
            } else {
                return false;
            }
        }
        if (result.isDenied) {
            if (isFunction(deniedFunc)) {
                window[deniedFunc]();
            } else {
                return false;
            }
        }
    })
}

function SWQalert(title, confirmFunc) {
Swal.fire({
    title: title,
    input: 'text',
    inputAttributes: {
      autocapitalize: 'off'
    },
    showCancelButton: true,
    confirmButtonText: '입력',
    showLoaderOnConfirm: true,
    allowOutsideClick: false
  }).then((result) => {
    if (result.isConfirmed) {
        if (isFunction(confirmFunc)) {
            window[confirmFunc](result);
        } else {
            return false;
        }
    }
  })
}