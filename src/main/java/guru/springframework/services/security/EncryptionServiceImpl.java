package guru.springframework.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	@Autowired
	private StrongPasswordEncryptor encryptor;
	
	@Override
	public String encryptString(String input) {
		return encryptor.encryptPassword(input);
	}

	@Override
	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		return encryptor.checkPassword(plainPassword, encryptedPassword);
	}

}
