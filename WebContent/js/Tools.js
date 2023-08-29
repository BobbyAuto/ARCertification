
/**

Generate a pair of private key and public key.
*/
function generateKeyPair() {
  // Initialize JSEncrypt instance
  var crypt = new JSEncrypt();

  // Generate key pair with a desired key size (default is 2048)
  crypt.getKey();

  // Get the public key in PEM format
  var publicKey = crypt.getPublicKey();

  // Get the private key in PEM format
  var privateKey = crypt.getPrivateKey();

  return { publicKey: publicKey, privateKey: privateKey };
}


/**
Sign a message with spacific private key
*/
function signMessage(privateKey, message) {
  var crypt = new JSEncrypt();
  crypt.setPrivateKey(privateKey);

  var signature = crypt.sign(message, CryptoJS.SHA256, "sha256");
  return signature;
}


/**
Varify the signature
*/
function verifySignature(publicKey, message, signature) {
  var crypt = new JSEncrypt();
  crypt.setPublicKey(publicKey);

  return crypt.verify(message, signature, CryptoJS.SHA256);
}
