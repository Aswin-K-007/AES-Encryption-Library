🔐 AES Key Generator Utility

A lightweight command-line tool for generating RSA private keys and self-signed public certificates used in hybrid encryption systems.

This utility is designed to support secure communication between services by generating cryptographic keys used for encryption and decryption.

The project uses Bouncy Castle for certificate and key generation.

🚀 Features

Generate RSA Private Keys (.pem)

Generate Self-Signed Public Certificates (.cert / .crt)

Configurable certificate validity

Simple CLI interface

Lightweight standalone executable JAR

Supports custom output directory

🛠 Requirements

Java 17+

Apache Maven

Verify installation:

java -version
mvn -version
📦 Build the Project

Clone the repository:

git clone https://github.com/Aswin-K-007/AES-Encryption-Library.git

Navigate to the project directory:

cd AES-Encryption-Library

Build the project:

mvn clean package

The executable JAR will be created at:

target/aes-keygen-lib-0.0.1.jar

▶️ Usage

Run the key generator with the following command:

java -jar aes-keygen-lib-0.0.1.jar <output-directory> <public-cert-name> <private-key-name> <validity-days>
📌 Example

Generate keys in the current directory:

java -jar aes-keygen-lib-0.0.1.jar . test-publickey.cert test-privatekey.pem 365

This will generate:

test-publickey.cert
test-privatekey.pem

📄 Parameters

Parameter	Description

output-directory  :	Folder where keys will be generated

public-cert-name  : Name of the public certificate

private-key-name	: Name of the private key

validity-days	    : Certificate validity in days


Example with custom folder:

java -jar aes-keygen-lib-0.0.1.jar ./certs bank-public.cert bank-private.pem 365

📂 Output Files

File	Description

.pem	RSA Private Key

.cert / .crt	Self-Signed Public Certificate

These keys are typically used in:

Hybrid encryption systems

Secure API communication

Microservice authentication


👨‍💻 Author

Developed by Aswin
