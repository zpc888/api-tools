package com.prot.apitool;


import java.util.Base64;

public class Base64MimeDecoder {
    public static void main(String[] args) {
        String cipher = "Ly8gZ3JvdXAgImNvbS5wcm90LmJ1aWxkLnRvb2wiCi8vIHZlcnNpb24gIiR7\ndmVyc2lvbn0iCgpjb25maWd1cmF0aW9ucyB7CiAgICBvbmVMaWIKfQoKYXJ0\naWZhY3RzIHsKICAgIG9uZUxpYiBmaWxlKCJwYXRoLXRvL29uZS1saWIuamFy\nIikKfQoKYXBwbHkgcGx1Z2luOiAnbWF2ZW4nCgpwcm9qZWN0LnRhc2tzLmNy\nZWF0ZSgndXBsb2FkT25lTGliJywgVXBsb2FkLmNsYXNzKSB7Cgljb25maWd1\ncmF0aW9uID0gY29uZmlndXJhdGlvbnMub25lTGliCiAgICByZXBvc2l0b3Jp\nZXMgewogICAgICAgIG1hdmVuRGVwbG95ZXIgewogICAgICAgICAgICByZXBv\nc2l0b3J5KHVybDogIiR7cHJvdFJlbGVhc2VVcmx9IikgewoJCQkJYXV0aGVu\ndGljYXRpb24odXNlck5hbWU6ICIke3Byb3RSZWxlYXNlVXNlcn0iLCBwYXNz\nd29yZDogIiR7cHJvdFJlbGVhc2VQc3dkfSIpCgkJCX0KCQkJcG9tLnZlcnNp\nb24gPSAiMC4wLjEiCgkJCXBvbS5hcnRpZmFjdElkID0gIm9uZS1saWIiCgkJ\nCXBvbS5ncm91cElkID0gImNvbS5vbmVsaWIiCgkJfQogICAgfQp9Cgo=\n";
        System.out.println("decode: " + cipher);
        byte[] decodedBytes = Base64.getMimeDecoder().decode(cipher);
        System.out.println(new String(decodedBytes));
    }
}
