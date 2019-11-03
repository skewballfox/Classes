import pygame
from string import ascii_lowercase
pygame.init()
class Chess_board(object):
    """general chess board gui, mainly meant to handle gui functions
    game logic is to be handled by the respective game class"""
    def __init__(self,types):
        """make an 8*8 board"""
        self.colors = [(131,131,131), (222,222,222)]
        board_dimensions = 480           # Proposed physical surface size.
        self.square_dimensions = board_dimensions / 8   
        self.board_dimensions = board_dimensions * self.square_dimensions     # Adjust to exactly fit n squares.
        self.surface = pygame.display.set_mode((board_dimensions, board_dimensions))
        #self.clock=pygame.time.clock()
        self.load_pieces(types)
        self.white_pieces=[]
        self.black_pieces=[]
        self.matrix = {}
        for letter in "ABCDEFGH":
            for i in range(1,9):
                self.matrix["{}{}".format(letter,i)]=Square()

    def draw_board(self):
        """Draw a generalized 8x8 board"""
        for Rank in range(8):           # Draw each row of the board.
            color_index = Rank % 2           # Alternate starting color
            for File in range(8):       # Run through cols drawing squares
                the_square = (File*self.square_dimensions, Rank*self.square_dimensions, self.square_dimensions, self.square_dimensions)
                self.surface.fill(self.colors[color_index], the_square)
                # Now flip the color index for the next square
                color_index = (color_index + 1) % 2
        for piece in self.white_pieces:
            self.surface.blit(self.images[(piece.type,piece.color)],(piece.get_num_file()*self.square_dimensions+self.offset[piece.type],piece.get_num_rank()*self.square_dimensions+self.offset[piece.type]))
        for piece in self.black_pieces:
                self.surface.blit(self.images[(piece.type,piece.color)],(piece.get_num_file()*self.square_dimensions+self.offset[piece.type],piece.get_num_rank()*self.square_dimensions+self.offset[piece.type]))

        pygame.display.flip()

    def load_pieces(self,types):
        self.offset={}
        self.images={}
        if 'pawn' in types:
            self.images[("pawn","white")]=self.pawn_white=pygame.transform.scale(pygame.image.load("pawn_white.png"),[50,30])
            self.images[("pawn","black")]=self.pawn_black=pygame.transform.scale(pygame.image.load("kisspng-battle-chess-queen-chess-piece-king-chess-game-5adecd0dd86422.0425014315245509258863.png"),[50,30])
            self.offset["pawn"]=(self.square_dimensions-self.imagespawn_white.get_width()) / 2


class Square(object):
    squares=[]
    for i in range(1,9):
        for letter in "ABCDEFGH":  
                squares.append("{}{}".format(letter,i))
    
    def __init__(self):
        self.name=Square.squares.pop()
        self.__occupation=False
        self.__owner=None
    
    @property
    def occupation(self):
        return self.__occupation
    @occupation.setter
    def occupation(self,status):
        self.__occupation=status
        if self.__occupation==False:
            self.__owner=None 

    @property
    def owner(self):
        return self.__owner
    @owner.setter
    def owner(self,piece):
        self.__owner=piece.color
        if self.__occupation==False:
            self.__occupation=True

class Piece:
    """General Piece for board games"""
    def __init__(self,color,location,type='pawn'):
        if color=="white" or color == 'w':
            self.color="white"
            self.direction=-1
        elif color=="black" or color =='b':
            self.color=="Black"
            self.direction=1
        if location[0] in 'ABCDEFGH':
            if int(location[1]) in range(1,9):
                self.location=location
        self.type=type
    
    def get_num_rank(self):
        return ord(self.location[0].ascii_lowercase)-97
    def get_num_file(self):
        return ord(self.location[1])

    def available_moves(self):
        return NotImplementedError

    def __del__(self):
        print("{} lost {} at {}{}".format(self.color,self.type,self.location[0],self.location[1]))