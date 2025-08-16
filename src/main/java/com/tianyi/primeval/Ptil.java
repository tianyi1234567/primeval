package com.tianyi.primeval;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

abstract public class Ptil {
    public static double sin (double angle) {
        return Math.sin(angle / 180 * Math.PI);
    }

    public static double cos (double angle) {
        return Math.cos(angle / 180 * Math.PI);
    }

    public static double tan (double angle) {
        if (angle == 90) throw new IllegalArgumentException("tan 90 is invalid!");
        return Math.tan(angle / 180 * Math.PI);
    }

    public static double arcsin (double value) {
        if (value > 1) throw new IllegalArgumentException("arcsin value > 1, which is invalid!");
        if (value < -1) throw new IllegalArgumentException("arcsin value < -1, which is invalid!");
        return Math.asin(value) / Math.PI * 180;
    }

    public static double arccos (double value) {
        if (value > 1) throw new IllegalArgumentException("arccos value > 1, which is invalid!");
        if (value < -1) throw new IllegalArgumentException("arccos value < -1, which is invalid!");
        return Math.acos(value) / Math.PI * 180;
    }

    public static double arctan (double value) {
        return Math.atan(value) / Math.PI * 180;
    }

    public static double getRtTriangleH(double H, double L) {
        return Math.sqrt(L * L - H * H);
    }

    public static double pow2(double x) {
        return Math.pow(x, 2);
    }

    public static int sign(double a) {
        return (int) Math.round(Math.abs(a) / a);
    }

    public static int sign(float a) {
        return (int) Math.round(Math.abs(a) / a);
    }

    public static int sign(int a) {
        return (int) Math.round(Math.abs(a) / a);
    }

    public static double distanceXOY(Vec2 v1, Vec2 v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2.0D) + Math.pow(v1.y - v2.y, 2.0D));
    }

    public static double distanceXOY(Vec3 v1, Vec3 v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2.0D) + Math.pow(v1.y - v2.y, 2.0D));
    }

    public static double distanceXYZ(Vec3 v1, Vec3 v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2.0D) + Math.pow(v1.y - v2.y, 2.0D) + Math.pow(v1.z - v2.z, 2.0D));
    }

    public static double clip(double input, double max, double min) {
        return Math.min(Math.max(input, min), max);
    }

    public static int clip(int input, int max, int min) {
        return Math.min(Math.max(input, min), max);
    }

    public static float clip(float input, float max, float min) {
        return Math.min(Math.max(input, min), max);
    }

//    public static double distanceP2S(Vec3 point, Vec3 begin, Vec3 end) {
//        Vec3 v = end.substract(begin);
//        if (v.length() == 0) {
//            return distanceXYZ(point, begin);
//        }
//        double u = (point.subtract(begin)).multiply(v) / pow2(v.length());
//        Vec3 minPoint = (u >= 0) ? ((u <= 1) ? v.scale(u).add(begin) : end) : begin;
//        return distanceXYZ(minPoint, point);
//    }

//    public static double distanceP2R(Vec3 point, Vec3 begin, Vec3 ray) {
//        if (ray.length() == 0) {
//            return distanceXYZ(point, begin);
//        }
//        double u = (point.subtract(begin)).multiply(ray) / pow2(ray.length());
//        Vec3 minPoint = (u >= 0) ? ray.scale(u).add(begin) : begin;
//        return distanceXYZ(minPoint, point);
//    }

//    public static double angleR2R(Vec3 ray1, Vec3 ray2) {
//        if (ray1.length() == 0 || ray2.length() == 0) return Math.PI / 2;
//        return arccos(ray1.multiply(ray2) / (ray1.length() * ray2.length()));
//    }
//
//    public static double distanceS2S(Vec3 begin1, Vec3 end1, Vec3 begin2, Vec3 end2) {
//        Vec3 v1 = end1.substract(begin1);
//        Vec3 v2 = end2.substract(begin2);
//        if (v1.length() == 0 && v2.length() != 0) {
//            return distanceP2S(begin1, begin2, end2);
//        } else if (v1.length() != 0 && v2.length() == 0) {
//            return distanceP2S(begin2, begin1, end1);
//        } else if (v1.length() == 0 && v2.length() == 0) {
//            return distanceXYZ(begin2, begin1);
//        }
//        Vec3 e1 = begin1.subtract(begin2);
//        double t1 = (v1.multiply(v2) * v2.multiply(e1) - pow2(v2.length()) * v1.multiply(e1))
//                / (pow2(v1.length() * v2.length()) - pow2(v1.multiply(v2)));
//        double t2 = - (v1.multiply(v2) * v1.multiply(e1) - pow2(v1.length()) * v2.multiply(e1))
//                / (pow2(v1.length() * v2.length()) - pow2(v1.multiply(v2)));
//        Vec3 minPoint1 = (t1 >= 0) ? ((t1 <= 1) ? v1.scale(t1).add(begin1) : end1) : begin1;
//        Vec3 minPoint2 = (t2 >= 0) ? ((t2 <= 1) ? v2.scale(t2).add(begin2) : end2) : begin2;
//        double dis1 = distanceP2S(minPoint1, begin2, end2);
//        double dis2 = distanceP2S(minPoint2, begin1, end1);
//        return Math.min(dis1, dis2);
//    }
//
//    public static boolean inRayRange(Vec3 originPos, Vec3 originRay, Vec3 pos, double pitch, double yaw) {
//        Vec3 vec = pos.subtract(originPos);
//        Vec3 vecXOZ = new Vec3(vec.x, 0, vec.z);
//        Vec3 rayXOZ = new Vec3(originRay.x, 0, originRay.z);
//        double yawR2V = angleR2R(vecXOZ, rayXOZ);
//
//        double pitchR2V = angleR2R(vec, vecXOZ) - angleR2R(originRay, rayXOZ);
//        if (yawR2V > yaw) return false;
//        if (pitchR2V < pitch || pitchR2V > 0) return false;
//        return true;
//    }
//    //FIXME
//    public static Vec3 getPredicatedVec3(Vec3 originPos, Vec3 originMov, Vec3 pos, double speed) {
//        if (speed <= 0) return new Vec3(0, 0, 0);
//        double h0 = distanceP2R(pos, originPos, originMov);
//        double h1 = getRtTriangleH(h0, distanceXYZ(pos, originPos));
//        double a = originMov.length() * originMov.length() - speed * speed;
//        double b = - 2 * originMov.length() * h1;
//        double c = h1 * h1 + h0 * h0;
//        double delta = b * b - 4 * a * c;
//        double t;
//        if (a == 0) {
//            t = -c / b;
//        } else if (delta >= 0) {
//            t = (-b - Math.sqrt(delta)) / (2 * a);
//        } else {
//            t = -1;
//        }
//        if (t < 0) return new Vec3(0, 0, 0);
//        return ((originPos.add(originMov.scale(t))).subtract(pos)).scale(1 / t);
//    }

    public static Vec3 xozVec2ToVec3(Vec2 v, double y) {
        return new Vec3(v.x, y, v.y);
    }

    public static Vec2 getClockwiseRotatedVec2(Vec2 referencePos, Vec2 pos, double rotatedAngle) {
        Vec3 vr = xozVec2ToVec3(referencePos, 0);
        Vec3 vp = xozVec2ToVec3(pos, 0);
        Vec3 initRay = vp.subtract(vr).normalize();
        double r = vp.subtract(vr).length();
        double initAngle = arccos(initRay.z) * (initRay.x) / Math.abs(initRay.x);
        double retX = sin(initAngle + rotatedAngle) * r + vr.x;
        double retY = cos(initAngle + rotatedAngle) * r + vr.z;
        return new Vec2((float) retX, (float) retY);
    }

    public static Vec3 round(Vec3 v, int digit) {
        double scale = Math.pow(10, digit);
        double x = Math.round(v.x * scale) / scale;
        double y = Math.round(v.y * scale) / scale;
        double z = Math.round(v.z * scale) / scale;
        return new Vec3(x, y ,z);
    }

    public static Vec2 round(Vec2 v, int digit) {
        double scale = Math.pow(10, digit);
        double x = Math.round(v.x * scale) / scale;
        double y = Math.round(v.y * scale) / scale;
        return new Vec2((float) x, (float) y);
    }
}