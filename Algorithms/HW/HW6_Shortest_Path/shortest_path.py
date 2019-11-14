from queue import SimpleQueue
"""using breadth first search
Author: Joshua Ferguson
Purpose: find the shortest path between a given node and all other nodes
get extra credit

I really need to implement snippets for docstrings
"""

def bfs_shortest_paths(graph,start_node):
    """shortest_paths(graph, start_node)
    
    find the shortest path from the start node to every other node in the graph
    
    Params
    ------
    
    graph :
        a dictionary where every key is a node and the value
        to each key is the adjacent nodes
    start_node 
    
    """
    queue=SimpleQueue()
    queue.put(start_node)
    
    discovered_paths={ key:'' for key in graph.keys() - start_node }
    
    while not queue.empty():

        current_node=queue.get()
    

        for adjacent_node in graph[current_node]:
            #if we just started
            print (current_node, ' ', adjacent_node)
            if current_node==start_node:
                discovered_paths[adjacent_node]=adjacent_node
                queue.put(adjacent_node)
            #if the node is unvisited
            elif discovered_paths[adjacent_node]=='':
                discovered_paths[adjacent_node]=(discovered_paths[current_node])+adjacent_node
                queue.put(adjacent_node)
            #if current path is shorter than previously found path
            elif len(discovered_paths[adjacent_node])>len((discovered_paths[current_node])+adjacent_node):
                discovered_paths[adjacent_node]=(discovered_paths[current_node])+adjacent_node
                queue.put(adjacent_node)
            
    return discovered_paths


if __name__=="__main__":
    graph={
        'A':['B'],
        'B':['C','E'],
        'C':['G','H','I'],
        'D':['B','E'],
        'E':['F'],
        'F':['D'],
        'G':['E'],
        'H':['G','I'],
        'I':['B'],
        'J':['B','D'],
    }

    paths=bfs_shortest_paths(graph,'J')
    print (paths)