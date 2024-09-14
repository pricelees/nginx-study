document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('photoRegistrationForm');
    if (form) {
        form.addEventListener('submit', registerForm);
    }
});

function registerForm(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 막습니다.

    const nickname = document.querySelector('input[name="nickname"]').value;
    const bestDate = document.querySelector('input[name="bestDate"]').value;
    const description = document.querySelector('textarea[name="description"]').value;

    const data = {
        nickname: nickname,
        bestDate: bestDate,
        description: description,
    };

    fetch('/registration', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.status === 200) {
                alert('성공적으로 제출되었습니다!');
            } else {
                throw new Error('제출 실패! 내용을 다시 확인해주세요!');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            alert(error.message);
        });
}
