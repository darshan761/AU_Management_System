import { Instructor } from './Instructor';
import { Timestamp } from 'rxjs/internal/operators/timestamp';

export class Course {
    courseId: number;
    courseName: string;
    courseSkill: string;
    courseLocation: string;
    coursePrerequisites: string;
    instructor: Instructor[];
    modifiedAt: string;
}