function getFileNames(files) {
    let names = [];
    for(let i=0; i<files.length; i++) {
        names.push(files[i].name)
    }
    return names;
}

function previewImage(file) {
    if (!file.type.startsWith('image/')) {
        return false;
    }

    const img = document.createElement("img");
    img.classList.add("obj");
    img.file = file;
    const reader = new FileReader();

    reader.onload = (function (aImg) {
        return function (e) {
            aImg.src = e.target.result;
        };
    })(img);

    reader.readAsDataURL(file);

    return img;
}

function removeFileFromFileList(fileId, index) {
    const dt = new DataTransfer()
    const input = document.getElementById(fileId)
    const { files } = input

    for (let i = 0; i < files.length; i++) {
        const file = files[i]
        if (index !== i)
            dt.items.add(file) // here you exclude the file. thus removing it.
    }

    input.files = dt.files // Assign the updates list
}