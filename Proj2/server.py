from model import Model
from agents import Sheep, Wolf, Grass
from mesa.visualization.modules import CanvasGrid, ChartModule
from mesa.visualization.ModularVisualization import ModularServer


def agent_portrayal(agent):
    if(type(agent) is Sheep):
        portrayal = {"Shape": "circle",
                    "Filled": "true",
                    "Layer": 0,
                    "Color": "blue",
                    "r": 0.5}
        return portrayal

    if(type(agent) is Wolf):
        portrayal = {"Shape": "circle",
                    "Filled": "true",
                    "Layer": 0,
                    "Color": "brown",
                    "r": 0.5}
        return portrayal
    
    if(type(agent) is Grass):
        portrayal = {"Shape": "circle",
                    "Filled": "true",
                    "Layer": 0,
                    "Color": "green",
                    "r": 0.5}
        return portrayal

grid = CanvasGrid(agent_portrayal, 50, 50, 500, 500)


sheep = {"Label": "Sheep", "Color": "blue"}
wolfs = {"Label": "Wolf", "Color": "brown"}
grass = {"Label": "Grass", "Color": "green"}
 

chart_count = ChartModule([sheep, wolfs, grass])
server = ModularServer(Model,
                       [grid,chart_count],
                       "Model",
                       {"S":100, "W":50, "G":150 , "width":50, "height":50})
server.port = 8521 # The default
server.launch()