import { Instructor } from './Instructor';

export class Course {
    courseId: number;
    courseName: string;
    courseSkill: string;
    courseLocation: string;
    coursePrerequisites: string;
    instructor: Instructor[];
}