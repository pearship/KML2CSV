clc
clear
close all

data = readtable('airports.csv','VariableNamingRule','preserve');

airport = table2cell(data(:,1));
link = table2cell(data(:,2));
node1 = table2cell(data(:,3));
lon1 = table2array(data(:,4));
lat1 = table2array(data(:,5));
type1 = table2cell(data(:,7));
node2 = table2cell(data(:,8));
lon2 = table2array(data(:,9));
lat2 = table2array(data(:,10));
type2 = table2cell(data(:,12));
type = table2cell(data(:,13));


nodeIds = node1;
nodeTypes = type1;
nodeCoords = [lon1 lat1];
for i = 1:length(node2)
    if isempty(find(strcmp(nodeIds, node2{i}), 1))
        n = height(nodeIds);
        nodeIds{n + 1} = node2{i};
        nodeTypes{n + 1} = type2{i};
        nodeCoords(n + 1, 1) = lon2(i);
        nodeCoords(n + 1, 2) = lat2(i);
    end
end

figure
hold on
for i = 1:length(link)
    if strcmp(type(i), 'Runway') == 1
        line([lon1(i) lon2(i)], [lat1(i) lat2(i)], 'color', 'black')
    end
end
for i = 1:length(nodeIds)
    if strcmp(nodeTypes(i), 'Runway') == 1
        plot(nodeCoords(i,1), nodeCoords(i,2), 'k.', 'markersize', 10)
        text(nodeCoords(i,1), nodeCoords(i,2), nodeIds(i))
    end
end
axis equal
hold off