import java.awt.*;
import java.util.*;

public class ChessBoard
{
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    private Piece contents[][];
    private boolean turn;
    private Vector legalMoves;
    public Vector allMoves;

    /* check info */
    private Point blackKing;
    private Point whiteKing;

    /* Castling info */
    public boolean whiteKingMoved;
    public boolean blackKingMoved;
    public boolean whiteKRookMoved;
    public boolean whiteQRookMoved;
    public boolean blackKRookMoved;
    public boolean blackQRookMoved;


    public ChessBoard()
    {
        contents = new Piece[8][8];
        turn = WHITE;
        allMoves = new Vector();

        /* Set up the board */
        newBoard();

        whiteKing = new Point(0, 4);
        blackKing = new Point(7, 4);
        whiteKingMoved = false;
        blackKingMoved = false;
        whiteKRookMoved = false;
        whiteQRookMoved = false;
        blackKRookMoved = false;
        blackQRookMoved = false;
        legalMoves = new Vector();
    }

    /* constructor for making copy */
    public ChessBoard(ChessBoard b)
    {
        int rank, file;
        Point p = new Point();
        contents = new Piece[8][8];
        turn = b.getTurn();
        for (rank = 0; rank < 8; rank++)
            for (file = 0; file < 8; file++)
            {
                p.rank = rank;
                p.file = file;
                if (b.isOccupied(p))
                    contents[rank][file] = b.getPiece(p);
            }
        whiteKing = b.getKing(WHITE);
        blackKing = b.getKing(BLACK);

        /* Castling info */
        whiteKingMoved = b.whiteKingMoved;
        whiteKRookMoved = b.whiteKRookMoved;
        whiteQRookMoved = b.whiteQRookMoved;
        blackKingMoved = b.blackKingMoved;
        blackKRookMoved = b.blackKRookMoved;
        blackQRookMoved = b.blackQRookMoved;

        /* copy the moves vector */
        allMoves = new Vector();
        Enumeration enum =b.allMoves.elements();
        while ( enum.hasMoreElements())
        allMoves.addElement( enum.nextElement());

        /* copy the legal moves vector */
        legalMoves = new Vector();
        enum =b.legalMoves.elements();
        while ( enum.hasMoreElements())
        legalMoves.addElement( enum.nextElement());


    }

    public void findAllLegalMoves()
    {
        int rank, file;
        Point temp = new Point();
        Vector v;
        Enumeration enum ;
        Move m;
        if (legalMoves != null)
            legalMoves.removeAllElements();
        for (rank = 0; rank < 8; rank++)
            for (file = 0; file < 8; file++)
            {
                temp.rank = rank;
                temp.file = file;
                if (isOccupied(temp) && getPiece(temp).getColor() == turn)
                {
                    v = contents[rank][file].getLegalMoves(temp, this);
                    enum =v.elements();
                    while ( enum.hasMoreElements())
                    {
                        m = (Move) enum.nextElement();
                        if (isCheckLegal(m))
                            legalMoves.addElement(m);
                    }

                }
            }
    }

    public void takeBackMove()
    {
        /* This function clears the board and starts over */
        /* Not the best way, but easy */
        Vector v = new Vector();
        Enumeration enum ;
        Move m;

        newBoard();
        if (!allMoves.isEmpty())
        {
            allMoves.removeElementAt(allMoves.size() - 1);
            enum =allMoves.elements();
            while ( enum.hasMoreElements())
            v.addElement( enum.nextElement());

            /* This is necessary because makeMove adds the moves onto the vector */
            allMoves.removeAllElements();

            enum =v.elements();
            while ( enum.hasMoreElements())
            makeMove((Move) enum.nextElement());

        }
        allMoves = v;
    }

    public void newBoard()
    {
        int i;
        /* erase pieces from middle */
        int rank, file;
        for (rank = 2; rank < 6; rank++)
            for (file = 0; file < 8; file++)
                contents[rank][file] = null;

        /* pawns */
        for (i = 0; i < 8; i++)
        {
            contents[1][i] = new Pawn(WHITE);
            contents[6][i] = new Pawn(BLACK);
        }


        contents[0][0] = new Rook(WHITE);
        contents[0][1] = new Knight(WHITE);
        contents[0][2] = new Bishop(WHITE);
        contents[0][3] = new Queen(WHITE);
        contents[0][4] = new King(WHITE);
        contents[0][5] = new Bishop(WHITE);
        contents[0][6] = new Knight(WHITE);
        contents[0][7] = new Rook(WHITE);

        contents[7][0] = new Rook(BLACK);
        contents[7][1] = new Knight(BLACK);
        contents[7][2] = new Bishop(BLACK);
        contents[7][3] = new Queen(BLACK);
        contents[7][4] = new King(BLACK);
        contents[7][5] = new Bishop(BLACK);
        contents[7][6] = new Knight(BLACK);
        contents[7][7] = new Rook(BLACK);

        turn = WHITE;

    }

    public boolean getTurn()
    {
        return turn;
    }


    public Point getKing(boolean which)
    {
        Point p;
        if (which)
            return new Point(whiteKing.rank, whiteKing.file);
        else
            return new Point(blackKing.rank, blackKing.file);
    }


    public boolean isLegal(Move m)
    {
        Vector v;
        Enumeration enum ;
        boolean value = false;
        Move temp;

        v = contents[m.from.rank][m.from.file].getLegalMoves(m.from, this);
        enum =v.elements();
        while ( enum.hasMoreElements() && !value)
        {
            temp = (Move) enum.nextElement();
            if (m.equals(temp))
            {
                value = true;
                /* if move is en passant, then the capture must be added */
                if (temp.enpKill != null)
                {
                    m.enpKill = new Point(temp.enpKill);
                }

                /* if move is promotion */
                if (temp.promotion)
                    m.promotion = true;
                /* if move is castling */
                m.setCastle(temp.getCastle());


            }
        }
        return value;
    }

    public boolean isOccupied(Point p)
    {
        return (contents[p.rank][p.file] != null);
    }

    public void makeMove(Move m)
    {
        contents[m.to.rank][m.to.file] = contents[m.from.rank][m.from.file];

        /* check for castling */
        if (m.getCastle() != (char) 0)
        {
            int homerank = m.from.rank;
            int dx = m.getCastle() == 'Q' ? -1 : 1;

            /* move the king */
            contents[homerank][4 + 2 * dx] = contents[homerank][4];
            contents[homerank][4] = null;

            /* move the rook */
            contents[homerank][4 + dx] = contents[homerank][(7 * dx + 7) / 2];
            contents[homerank][(7 * dx + 7) / 2] = null;
        }

        /* check for en Passant */
        if (m.enpKill != null)
            contents[m.enpKill.rank][m.enpKill.file] = null;

        /* check for promotion */
        if (m.promotion)
            /* The AI does automatic promotion to Queen */
            switch (m.promoteTo)
            {
                case 'Q':
                    contents[m.to.rank][m.to.file] = new Queen(turn);
                    break;
                case 'B':
                    contents[m.to.rank][m.to.file] = new Bishop(turn);
                    break;
                case 'R':
                    contents[m.to.rank][m.to.file] = new Rook(turn);
                    break;
                case 'N':
                    contents[m.to.rank][m.to.file] = new Knight(turn);
                    break;
                default:
                    contents[m.to.rank][m.to.file] = new Queen(turn);
            }

        /* Check if the King or a Rook moved (castling purposes)*/
        if (m.from.equals(new Point(0, 4)))
            whiteKingMoved = true;
        if (m.from.equals(new Point(0, 0)))
            whiteQRookMoved = true;
        if (m.from.equals(new Point(0, 7)))
            whiteKRookMoved = true;

        if (m.from.equals(new Point(7, 4)))
            blackKingMoved = true;
        if (m.from.equals(new Point(7, 0)))
            blackQRookMoved = true;
        if (m.from.equals(new Point(7, 7)))
            blackKRookMoved = true;




        /* Add the move to the list of moves */
        allMoves.addElement(m);

        /* If the king has moved, update its position */
        if (m.from.equals(getKing(WHITE)))
        {
            whiteKing.rank = m.to.rank;
            whiteKing.file = m.to.file;
        }
        if (m.from.equals(getKing(BLACK)))
        {
            blackKing.rank = m.to.rank;
            blackKing.file = m.to.file;
        }


        /* change players */
        turn = !turn;

        /* empty the square that was moved from */
        contents[m.from.rank][m.from.file] = null;

    }

    public boolean isCheckLegal(Move m)
    {
        boolean returnValue = false;
        ChessBoard temp = new ChessBoard(this);
        /* The function is simple if not castling */
        if (m.getCastle() == (char) 0)
        {
            temp.makeMove(m);
            if (temp.isInCheck(!temp.getTurn()))
                m.setMessage("Move results in Check!");
            else
                returnValue = true;
        }

        /* if castling, check the intermediate squares */
        else
        {
            int homerank = turn == WHITE ? 0 : 7;
            int dx = (m.getCastle() == 'Q') ? -1 : 1;

            /* If not in immediate check */
            if (!temp.isInCheck(turn))
            {
                temp.makeMove(new Move(new Point(homerank, 4), new Point(homerank, 4 + dx)));
                /* If the middle square is not attacked */
                if (!temp.isInCheck(turn))
                {
                    temp.makeMove(new Move(new Point(homerank, 4 + dx), new Point(homerank, 4 + 2 * dx)));
                    /* If the final square is not attacked */
                    if (!temp.isInCheck(turn))
                        returnValue = true;
                }

            }
        }
        return returnValue;
    }

    public Move getLastMove()
    {
        if (allMoves.isEmpty())
            return null;
        else
            return (Move) allMoves.elementAt(allMoves.size() - 1);
    }

    public Piece getPiece(Point p)
    {
        return contents[p.rank][p.file];
    }

    public boolean isInCheck(boolean t)
    {
        int rank, file;
        boolean value = false;
        Vector v;
        Enumeration enum ;
        Point from = new Point();
        boolean switched = false;

        /* if it is t's turn, then make it temporarily !t's turn */
        if (turn == t)
        {
            switched = true;
            turn = !turn;
        }


        /* Get all legal moves (without worrying about checks) */
        for (rank = 0; rank < 8; rank++)
            for (file = 0; file < 8; file++)
            {
                from.rank = rank;
                from.file = file;
				
				/* for each square occupied by (turn), see if it is legal to 
				   move from the square to the opponents king */
                /* Do not include castling as a way of capture opp's king */
                if (isOccupied(from) && getPiece(from).getColor() == turn
                        && isLegal(new Move(from, getKing(t))))
                    value = true;
            }

        /* If we switched the turn to check for the other king, switch back */
        if (switched)
            turn = !turn;


        return value;
    }

    /* Does not check for checks in the path */
    public boolean canCastle(boolean t, char side)
    {
        boolean value = true;
        ChessBoard temp;

        if (t == WHITE && side == 'K')
        {
            if (whiteKingMoved || whiteKRookMoved
                    || isOccupied(new Point(0, 5)) || isOccupied(new Point(0, 6)))
                value = false;
            else
                value = true;
        }
        else
            if (t == WHITE && side == 'Q')
            {
                if (whiteKingMoved || whiteQRookMoved
                        || isOccupied(new Point(0, 3)) || isOccupied(new Point(0, 2))
                        || isOccupied(new Point(0, 1)))
                    value = false;
                else
                    value = true;
            }

            else
                if (t == BLACK && side == 'K')
                {
                    if (blackKingMoved || blackKRookMoved
                            || isOccupied(new Point(7, 5)) || isOccupied(new Point(7, 6)))
                        value = false;
                    else
                        value = true;
                }
                else
                    if (t == BLACK && side == 'Q')
                    {
                        if (blackKingMoved || blackQRookMoved
                                || isOccupied(new Point(7, 3)) || isOccupied(new Point(7, 2))
                                || isOccupied(new Point(7, 1)))
                            value = false;
                        else
                            value = true;
                    }
        return value;
    }

    public Vector getAllLegalMoves()
    {
        return legalMoves;
    }

    public Vector getBestMoves(int depth)
    {

        Vector v = new Vector();
        int ratings[];
        ratings = new int[legalMoves.size()];
        int index = 0;
        int max = -1000;  // value of best move
        Enumeration enum =legalMoves.elements();
        Move m;

        /* Go through all legal moves */
        while ( enum.hasMoreElements())
        {
            m = (Move) enum.nextElement();
            ratings[index] = rateMove(m, depth);
            if (ratings[index] > max)
                max = ratings[index];
            index++;
        }

        /* Make a vector with the optimal moves for return */
        for (int i = 0; i < index; i++)
            if (ratings[i] == max)
                v.addElement(legalMoves.elementAt(i));

        return v;
    }

    private int rateMove(Move m, int depth)
    {
        int value = 0;
        /* base case */
        if (depth == 0)
        {
            /* promotion is worth a new Queen */
            if (m.promotion)
                value += 90;

            if (isOccupied(m.to))
                value += 10 * getPiece(m.to).getValue();

                /* en Passant is special case */
            else
                if (m.enpKill != null)
                    value += 10;

                    /* castling is worth an arbitrary 50 */
                else
                    if (m.castle != (char) 0)
                        value += 50;

                        /* take away a bit for moving king */
                    else
                        if (getPiece(m.from).getValue() == 1000)
                            value -= 10;

            /* Attach an small value to center squares */
            value += (Math.abs(7 - m.to.rank) + Math.abs(7 - m.to.file)
                    - Math.abs(7 - m.from.rank) - Math.abs(7 - m.from.file)) / 2;

            return value;
        }

        /* Recursive case */
        else
        {
            ChessBoard copy = new ChessBoard(this);

            /* Get the rating for just this move */
            int nominal = rateMove(m, 0);

            /* Make the actual move */
            copy.makeMove(m);

            /* find all recourses */
            copy.findAllLegalMoves();

            /* If there are no legal moves + check...*/
            if (copy.getAllLegalMoves().size() == 0)
            {
                if (copy.isInCheck(copy.getTurn()))
                    return 1000 + depth; // Mate in two is better than mate in 1
                else
                    return copy.isAhead(turn) ? -100 : 100;
            }

            /* Otherwise go through all legal moves */
            else
            {
                int maxRating = -1000;
                int rating;
                Enumeration enum =copy.getAllLegalMoves().elements();
                while ( enum.hasMoreElements())
                {
                    rating = copy.rateMove((Move) enum.nextElement(), depth - 1);
                    if (rating > maxRating)
                        maxRating = rating;
                }

                return (nominal - maxRating);
            }
        }
    }

    /* Simply calculates the values of all pieces */
    public boolean isAhead(boolean t)
    {
        int rank, file, white = 0, black = 0;
        Point temp = new Point();
        Piece p;
        for (rank = 0; rank < 8; rank++)
            for (file = 0; file < 8; file++)
            {
                temp.rank = rank;
                temp.file = file;
                if (isOccupied(temp))
                {
                    if ((p = getPiece(temp)).getColor())
                        white += p.getValue();
                    else
                        black += p.getValue();
                }
            }

        return (white > black);
    }

}