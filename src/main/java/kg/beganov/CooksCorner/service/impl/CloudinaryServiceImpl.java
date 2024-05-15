package kg.beganov.CooksCorner.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.beganov.CooksCorner.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "do3joihks",
                "api_key", "183873297347987",
                "api_secret", "9OhJ6rcogLtTX54eWcIKLV5svf4"));
    }
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("secure_url");
    }
    @Override
    public void deleteProductImage(String imageUrl) throws IOException {
        String publicId = extractPublicId(imageUrl);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
    public String extractPublicId(String imageUrl) {
        String withoutProtocol = imageUrl.replaceFirst("https?://[^/]+/", "");
        String withoutPath = withoutProtocol.substring(0, withoutProtocol.lastIndexOf("/"));
        return withoutPath.substring(withoutPath.lastIndexOf("/") + 1, withoutPath.lastIndexOf("."));
    }
}

