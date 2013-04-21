package ru.nsu.gordin.model;

/**
 * Created with IntelliJ IDEA.
 * User: gormakc
 * Date: 4/2/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */

public enum Directions
{
    LEFT, RIGHT, UP, DOWN;

    /*проверяет только в пределах карты*/
//    static public boolean updateCoordinates(ru.nsu.gordin.model.Directions d, int x, int y/*, Void i*/, int width, int heigth)
//    {
//        switch (d)
//        {
//            case LEFT :
//            {
//                if(y > 0)
//                {
//                    return true;
//                }
//            }
//            case RIGHT :
//            {
//                if(y < width - 1)
//                {
//                    return true;
//                }
//            }
//            case UP :
//            {
//                if(x > 0)
//                {
//                    return true;
//                }
//            }
//            case DOWN :
//            {
//                if(x < heigth - 1)
//                {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
