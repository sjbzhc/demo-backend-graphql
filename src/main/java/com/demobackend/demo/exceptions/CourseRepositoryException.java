package com.demobackend.demo.exceptions;

public class CourseRepositoryException extends DemoException {
    public static class RepositoryAccessException extends CourseRepositoryException {}
}
