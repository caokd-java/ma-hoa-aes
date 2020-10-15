

function onChangeValueMaHoa() {
  const chuoiMaHoa = document.getElementById('str2Encrypt').value;
  const matKhauMaHoa = document.getElementById('password2Encrypt').value;

  if (chuoiMaHoa === '' || matKhauMaHoa === '') {
    document.getElementById('btnMaHoa').disabled = true;
  } else {
    document.getElementById('btnMaHoa').disabled = false;
  }
}

function onChangeValueGiaiMa() {
  const chuoiMaHoa = document.getElementById('str2Decrypt').value;
  const matKhauMaHoa = document.getElementById('password2Decrypt').value;

  if (chuoiMaHoa === '' || matKhauMaHoa === '') {
    document.getElementById('btnGiaiMa').disabled = true;
  } else {
    document.getElementById('btnGiaiMa').disabled = false;
  }
}

function encrypt() {

  const chuoiMaHoa = document.getElementById('str2Encrypt').value;
  const matKhauMaHoa = document.getElementById('password2Encrypt').value;

  const param = {
    chuoiMaHoa: chuoiMaHoa,
    matKhauMaHoa: matKhauMaHoa,
    ketQuaMaHoa: ''
  };

  const xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
      const response = JSON.parse(xmlHttp.responseText);
      document.getElementById('resultEncrypt').value = response.ketQuaMaHoa;
    }
  };

  xmlHttp.open("POST", 'http://localhost:8080/ma-hoa', true); // true for asynchronous
  xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xmlHttp.send(JSON.stringify(param));
}

function decrypt() {
  const chuoiCanGiaiMa = document.getElementById('str2Decrypt').value;
  const matKhauGiaiMa = document.getElementById('password2Decrypt').value;

  const param = {
    chuoiCanGiaiMa: chuoiCanGiaiMa,
    matKhauGiaiMa: matKhauGiaiMa,
    ketQuaGiaiMa: ''
  };

  const xmlHttp = new XMLHttpRequest();
  xmlHttp.onreadystatechange = function() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
      const response = JSON.parse(xmlHttp.responseText);
      document.getElementById('resultDecrypt').value = response.ketQuaGiaiMa;
    }
  };

  xmlHttp.open("POST", 'http://localhost:8080/giai-ma', true); // true for asynchronous
  xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xmlHttp.send(JSON.stringify(param));
}
